package com.mx.web.demo;

import com.mx.util.FtpFileUtils;
import com.mx.util.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mx on 2017/12/15.
 */
@RestController
public class FileDemoContoller {
    private Logger logger = LoggerFactory.getLogger(ParamDemoController.class);

    @Autowired
    private FtpFileUtils ftpFileUtils;

    @ApiOperation(value="测试参数异常", notes="测试参数异常")
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    public ResultEntity upload(@RequestBody MultipartFile file){
        ResultEntity result = new ResultEntity();
        try {
            ftpFileUtils.upload(file);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        result.setSuccess(true);
        result.setMsg( "成功" );
        return result;
    }
}
