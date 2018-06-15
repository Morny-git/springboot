package com.mx.util;

/**
 * Created by hadoop on 2017/3/23.
 */
public enum StateCode{

    AUTHORIZATION_ERROR(400, "无权限"),
    METHOD_ERROR(405, "方法错误"),

    USERNAME_ERROR(1010, "用户名错误"),
    PASSWORK_ERROR(1011,"密码错误"),

    PARAM_ERROR(601, "参数不合法"),
    TOKEN_ERROR(605, "无效的Token"),
    NO_AUTH_ERROR(606, "无操作权限"),
    LOCK_ERROR(604, "用户帐号冻结");

    private Integer stateCode;
    private String stateExp;

    private StateCode(Integer stateCode, String stateExp) {
        this.stateCode = stateCode;
        this.stateExp = stateExp;
    }
    public static ResultEntity retStateCode(Integer stateCode, String stateExp) {
        ResultEntity resultEntity = new ResultEntity(stateCode,stateExp);
        return resultEntity;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateExp() {
        return stateExp;
    }

    public void setStateExp(String stateExp) {
        this.stateExp = stateExp;
    }
}
