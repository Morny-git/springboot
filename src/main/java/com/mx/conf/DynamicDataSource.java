package com.mx.conf;

/**
 * Created by mx on 2017/11/3.
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* 动态数据源（需要继承AbstractRoutingDataSource）
 * 用DatabaseContextHolder获取当前线程的DatabaseType
*/
public class DynamicDataSource extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
