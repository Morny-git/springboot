package com.mx.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by hadoop on 2016/3/17.
 */
@Data
public class ResultEntity {

    /**
     * 是否操作成功
     */
    private Boolean success;
    /**
     * 数据
     */
    private Object data;

    /**
     * 分页参数
     */
    private PageInfo<?> pageInfo;

    /**
     * 错误代码
     */
    private int errorCode;

    /**
     * 提示信息
     */
    private String msg;

    public ResultEntity() {
        this.success = true;
    }

    public ResultEntity(String msg) {
        this.success = false;
        this.errorCode = 500;
        this.msg = msg;
    }

    public ResultEntity(Object data) {
        this.success = true;
        this.data = data;
    }

    public ResultEntity(int errorCode, String msg) {
        this.success = false;
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public ResultEntity(Boolean success, String msg, Object data) {
        this.success = success;
        this.errorCode = 500;
        this.msg = msg;
        this.data = data;
    }


    public ResultEntity(Boolean success, int errorCode, String msg, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

}
