package com.mx.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mx on 2017/7/3.
 */
@Data
public class RedisEntity implements Serializable {
    private static final long serialVersionUID = -1L;

    private String userName;
    private String passWord;

    public RedisEntity(String userName,String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
}
