package com.mx.mapper;




import com.mysql.fabric.xmlrpc.base.Params;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
    //新增
    int add(T t);
    //修改
    int edit(T t);
    //批量删除
    int deleteBatch(Map<String, Object> map);
    //删除
    int delete(String id);
    //删除
    int deleteByPk2(String pk1, String pk2);
    //通过id查找实体
    T findById(String id);
    //查询列表
    List<T> findList(Params params);
    //批量新增
    int addBatch(List<T> list);
    //批量修改
    int editBatch(List<T> list);
    //批量新增修改
    int saveOrUpdateBatch(List<T> list);

}
