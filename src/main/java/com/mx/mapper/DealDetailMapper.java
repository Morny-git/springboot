package com.mx.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mx on 2017/8/31.
 */
@Repository
public interface DealDetailMapper {

    //获取实时订单的交易位置
    List<Map<String,Object>> realtime(@org.apache.ibatis.annotations.Param("createTime") String createTime);
    //各市交易额排名
    List<Map<String,Object>> sellerAreaRank();
    //各商家交易额排名
    List<Map<String,Object>> sellerRank();
    //热卖商品排名
    List<Map<String,Object>> goodsRank();
    //不同电商平台的订单数量
    List< Map<String,Object>> platformDealRank();
    //总交易额
    Map<String,Object> totalSaleAmount();

}
