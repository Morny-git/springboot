package com.mx.domain;

/**
 * Created by mx on 2017/9/26.
 */
public class WebClass {
    //分类编码
    private String code;
    //分类名称
    private String className;
    //停止标识
    private int stopStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(int stopStatus) {
        this.stopStatus = stopStatus;
    }
}
