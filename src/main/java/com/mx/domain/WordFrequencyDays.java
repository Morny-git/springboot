package com.mx.domain;

/**
 * Created by mx on 2017/9/26.
 */
public class WordFrequencyDays {

    //热词
    private String word;
    //词频
    private int frequency;
    //入库时间
    private String createTime;
    //阈值低
    private int threshold1;
    //阈值中
    private int threshold2;
    //阈值高
    private int threshold3;

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(int threshold1) {
        this.threshold1 = threshold1;
    }

    public int getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(int threshold2) {
        this.threshold2 = threshold2;
    }

    public int getThreshold3() {
        return threshold3;
    }

    public void setThreshold3(int threshold3) {
        this.threshold3 = threshold3;
    }
}
