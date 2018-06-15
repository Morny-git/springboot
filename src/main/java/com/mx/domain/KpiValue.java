package com.mx.domain;

/**
 * Created by mx on 2017/10/19.
 */
public class KpiValue {
    private String areaName;
    private String kpiCode;
    private String kpiName;
    private String year;
    private double kpiValue;
    private double pKpiValue;
    private double ratio;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(double kpiValue) {
        this.kpiValue = kpiValue;
    }

    public double getpKpiValue() {
        return pKpiValue;
    }

    public void setpKpiValue(double pKpiValue) {
        this.pKpiValue = pKpiValue;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
