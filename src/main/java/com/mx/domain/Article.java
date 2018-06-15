package com.mx.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by mx on 2017/9/26.
 */
public class Article {
    //网站编码
    private String wCode;
    //文章url
    private String articleUrl;
    //文章标题
    private String names;
    //入库时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public String getwCode() {
        return wCode;
    }

    public void setwCode(String wCode) {
        this.wCode = wCode;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
