package com.mx.conf;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * MyBatis基础配置
 * springboot集成mybatis的基本入口
 * 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
 *2）创建SqlSessionFactory
 * 3）配置事务管理器，除非需要使用事务，否则不用配置
 */
@Configuration
@MapperScan(basePackages = "com.mx.mapper")
@EnableTransactionManagement
public class MyBatisConfig {
    @Autowired
    private Environment env;

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource ds1DataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        props.put("url", env.getProperty("spring.first.datasource.url"));
        props.put("username", env.getProperty("spring.first.datasource.username"));
        props.put("password", env.getProperty("spring.first.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource ds2DataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        props.put("url", env.getProperty("spring.second.datasource.url"));
        props.put("username", env.getProperty("spring.second.datasource.username"));
        props.put("password", env.getProperty("spring.second.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("ds1DataSource") DataSource ds1DataSource,
                                        @Qualifier("ds2DataSource") DataSource ds2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.ds1, ds1DataSource);
        targetDataSources.put(DatabaseType.ds2, ds2DataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(ds1DataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//
        //是否可以使用列的别名（） （取决于驱动的兼容性） default：true
        fb.getObject().getConfiguration().setUseColumnLabel(true);
        //是否启用数据中A_column 自动映射到java类中驼峰命名的属性 default：false
        fb.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        //设置但JDBC类型为空时，某些驱动程序要指定值 default：OTHER
        fb.getObject().getConfiguration().setJdbcTypeForNull(null);
        //在null时也调用setter，适应于返回map，3.2以上版本可用
        fb.getObject().getConfiguration().setCallSettersOnNulls(true);
        //是否使用懒加载 关联对象，同hiberbate中的延迟加载一样 default：true
        fb.getObject().getConfiguration().setLazyLoadingEnabled(true);
        //当对象使用延迟加载时，属性的加载取决于被引用到的那些延迟属性，否则，按需加载（需要时才加载）
        fb.getObject().getConfiguration().setAggressiveLazyLoading(true);
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
