package com.mx.service.impl;

import com.mx.mapper.ArticleMapper;
import com.mx.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mx on 2017/9/27.
 */
@Service
public class WordService implements IWordService {
    @Autowired
    private ArticleMapper articleMapper;

    //网站热门文章
    public Map<String, List<Map<String, Object>>> web() {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> webs = articleMapper.web();
        for (Map<String, Object> web : webs) {
            if (map.containsKey(web.get("createTime"))){
                map.get(web.get("createTime")).add(web);
            }else {
                List<Map<String, Object>> list = new ArrayList<>();
                list.add(web);
                map.put((String) web.get("createTime"),list);
            }
        }
        return map;
    }

}
