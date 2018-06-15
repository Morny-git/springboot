package com.mx.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by hadoop on 2016/5/5.
 */
@Data
public class BaseEntity extends com.mx.domain.PageEntity {
    // 注解含义：数据库表没有该字段，如：`pageNum` `pageSize`则需要增加 @Transient 注解
    @Transient
    protected int pageNum;
    @Transient
    protected int pageSize;

    protected String createrId;

    protected String createrName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    protected String lastUpdaterId;

    protected String lastUpdaterName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date lastUpdateTime;

    protected String remarks;

    protected int ord;

}
