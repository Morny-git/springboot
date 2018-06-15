package com.mx.web.demo;

import com.github.pagehelper.PageInfo;
import com.mx.domain.ParamEntity;
import com.mx.service.ICodeService;
import com.mx.util.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mx on 2017/6/30.
 */
@RestController
@RequestMapping("page")
public class PageController {
    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private ICodeService codeService;

    @ApiOperation(value="分页获取多条数据demo", notes="分页获取多条数据demo")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ResultEntity list(@RequestBody ParamEntity paramEntity) throws Exception{
        ResultEntity result = new ResultEntity();
        PageInfo pageInfo = new PageInfo<>(codeService.builPeriod(paramEntity));
        result.setSuccess(true);
        result.setMsg( "成功" );
        result.setData(pageInfo);
        return result;
    }

}
