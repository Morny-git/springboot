package com.mx.service;
import java.util.Map;

/**
 * Created by mx on 2017/8/31.
 */
public interface IGoodsService {

    //获取首页四个排名
    Map<String,Object> indexForRank() throws Exception;

}
