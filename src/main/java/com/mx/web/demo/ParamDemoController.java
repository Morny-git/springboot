package com.mx.web.demo;

import com.mx.domain.ParamEntity;
import com.mx.util.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by mx on 2017/6/30.
 */
@RestController
@Validated
public class ParamDemoController {
    private Logger logger = LoggerFactory.getLogger(ParamDemoController.class);

    @ApiOperation(value="测试参数异常", notes="测试参数异常")
    @RequestMapping(value = "testParam" ,method = RequestMethod.POST)
    public ResultEntity testParam( @Valid @RequestBody ParamEntity param){
        ResultEntity result = new ResultEntity();
        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(param);
        return result;
    }
    @ApiOperation(value="测试参数异常", notes="测试单个参数异常")
    @RequestMapping(value = "check" ,method = RequestMethod.GET)
    public String check(@Min(value = 5,message = "kpId必须大于等于5") @RequestParam int kpId) {
        return "ok";
    }
    @ApiOperation(value="测试异常", notes="测试异常")
    @RequestMapping(value = "testException" ,method = RequestMethod.GET)
    public ResultEntity testException(){
        ResultEntity result = new ResultEntity();
        int i = 1/0;
        result.setSuccess(true);
        result.setMsg( "成功" );
        return result;
    }
    @ApiOperation(value="测试多个小数点的参数", notes="测试多个小数点的参数")
    @RequestMapping(value = "check/{version}" ,method = RequestMethod.GET)
    public String check1( @PathVariable String version) {
        System.out.println("0.0.3"+"----------"+version);
        return "ok";
    }

}
