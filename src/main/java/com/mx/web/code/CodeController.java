package com.mx.web.code;


import com.mx.domain.ParamEntity;
import com.mx.domain.CodeEntity;
import com.mx.service.ICodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    private ICodeService codeService;

    @ApiOperation(value = "建档周期",notes = "查询建档周期列表")
    @RequestMapping(value = "builPeriod" ,method = RequestMethod.GET)
    public List<CodeEntity> builPeriod(@RequestBody ParamEntity paramEntity) {

        return codeService.builPeriod(paramEntity);
    }



}
