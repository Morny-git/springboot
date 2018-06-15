package com.mx.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mx on 2017/9/26.
 */
@Repository
public interface ArticleMapper {
    List<Map<String,Object>> web();


}
