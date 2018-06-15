package com.mx.service.impl;


import com.mx.mapper.DealDetailMapper;
import com.mx.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mx on 2017/8/31.
 */
@Service
public class GoodsService implements IGoodsService {
    @Autowired
    private DealDetailMapper dealDetailMapper;

    @Override
    public Map<String, Object> indexForRank() throws Exception {
         Map<String, Object> map = new HashMap<>();

        //获取各个地域经纬度以及交易额
        List<Map<String, Object>> baseMap = dealDetailMapper.realtime(null);
        map.put("baseMap", baseMap);
        Map<String, Object> totalSaleAmounts = dealDetailMapper.totalSaleAmount();
        map.put("totalSaleAmount",totalSaleAmounts );
        //交易额地域排名
        map.put("sellerAreaRank", dealDetailMapper.sellerAreaRank());
       //商家交易额排名
        map.put("sellerRank", dealDetailMapper.sellerRank());
        //各电商数据量排名
        map.put("platformDealRank", dealDetailMapper.platformDealRank());
        //热卖商品排名
        map.put("goodsRank", dealDetailMapper.goodsRank());
        return map;
    }


}
