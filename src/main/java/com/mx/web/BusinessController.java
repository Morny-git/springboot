package com.mx.web;

import com.mx.service.IGoodsService;
import com.mx.util.ResultEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by mx on 2017/8/31.
 */
@RestController
@RequestMapping("/businiss")
public class BusinessController {
    private static Logger logger = Logger.getLogger(BusinessController.class);

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value = "baseMap", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity baseMap() {
        ResultEntity result = new ResultEntity();

        try {
            //排名
            long start1 = System.currentTimeMillis();
            Map<String, Object> map = goodsService.indexForRank();
            long start2 = System.currentTimeMillis();
            logger.info("00000000000---"+(start2-start1));
            result.setData(map);
            result.setSuccess(!map.isEmpty());
            result.setMsg(result.getSuccess() ? "成功" : "失败");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg("失败");
        }
        return result;


    }
}