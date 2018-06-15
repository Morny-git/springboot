package com.mx.domain;

/**
 * Created by mx on 2017/9/26.
 */
public class Thesaurus {
    //热词id
    private String id;
    //热词
    private String names;
    //阈值低
    private int threshold1;
    //阈值中
    private int threshold2;
    //阈值高
    private int threshold3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
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
