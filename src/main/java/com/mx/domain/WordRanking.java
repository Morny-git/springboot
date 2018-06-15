package com.mx.domain;

/**
 * Created by mx on 2017/9/26.
 */
public class WordRanking {
    //关键字
    private String word;
    //词频
    private double frequency;
    //入库时间
    private String createTime;
    //关联度
    private String relevancy;

    public String getRelevancy() {
        return relevancy;
    }

    public void setRelevancy(String relevancy) {
        this.relevancy = relevancy;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
