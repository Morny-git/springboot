package com.mx.web.demo;

import com.mx.domain.ParamEntity;
import com.mx.util.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by mx on 2017/6/30.
 */
@RestController
@RequestMapping("demo1")
public class RestfulController {
    private Logger logger = LoggerFactory.getLogger(RestfulController.class);

    @ApiOperation(value="新增demo", notes="新增demo")
    @RequestMapping(method = RequestMethod.POST)
    public ResultEntity add(@Valid @RequestBody ParamEntity param) throws Exception{
        ResultEntity result = new ResultEntity();

        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(param);
        return result;
    }
    @ApiOperation(value="修改demo", notes="修改demo")
    @RequestMapping(method = RequestMethod.PUT)
    public ResultEntity edit( @Valid @RequestBody ParamEntity param) throws Exception{
        ResultEntity result = new ResultEntity();

        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(param);
        return result;
    }

    @ApiOperation(value="单条删除demo", notes="单条删除demo")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResultEntity del( @PathVariable("id")  int id) throws Exception{
        ResultEntity result = new ResultEntity();

        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(id);
        return result;
    }

    @ApiOperation(value="多条删除demo", notes="多条删除demo")
    @RequestMapping(value = "delBatch",method = RequestMethod.POST)
    public ResultEntity delBatch(@RequestBody Map<String,Object> map) throws Exception{
        ResultEntity result = new ResultEntity();

        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(map);
        return result;
    }

    @ApiOperation(value="获取单条数据demo", notes="获取单条数据demo")
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResultEntity getById( @PathVariable("id") int id) throws Exception{
        ResultEntity result = new ResultEntity();

        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(id);
        return result;
    }

    @ApiOperation(value="获取多条数据demo", notes="获取多条数据demo")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ResultEntity list(@RequestBody Map<String,Object> map) throws Exception{
        ResultEntity result = new ResultEntity();

        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(map);
        return result;
    }

}
