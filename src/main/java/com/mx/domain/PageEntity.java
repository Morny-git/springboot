package com.mx.domain;

import lombok.Data;

import javax.persistence.Transient;

/**
 * Created by hadoop on 2016/5/5.
 */
@Data
public class PageEntity {
    // 注解含义：数据库表没有该字段，如：`pageNum` `pageSize`则需要增加 @Transient 注解
    @Transient
    protected int pageNum;
    @Transient
    protected int pageSize;

}
