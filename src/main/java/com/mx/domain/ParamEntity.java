package com.mx.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by mx on 2017/11/1.
 */
@Data
public class ParamEntity {
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$",message = "身份证格式不正确")
    private String idNo;

    private Integer pageSize;
     private Integer pageNum;
}
