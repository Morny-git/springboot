package com.mx.domain;

import lombok.Data;

/**
 * Created by Administrator on 2017/4/17.
 */
@Data
public class ArgumentInvalidResult {
    private String field;
    private Object rejectedValue;
    private String defaultMessage;
}
