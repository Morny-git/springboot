package com.mx.domain;

import org.springframework.data.annotation.Persistent;

import java.util.Date;

public class DealDetail {
    //详单id
    private String detailId;
    //插入时间
    private Date createTime;
    //卖家id
    private String sellerId;
    //卖家名
    private String sellerName;
    //卖家地域id
    private String sellerAreaId;
    //卖家地域名
    private String sellerAreaName;
    //买家id
    private String buyerId;
    //买家名
    private String buyerName;
    //卖家地域名
    private String buyerAreaName;
    //商品id
    private String goodsId;
    //商品
    private String goodsName;
    //销售量
    private double salesVolume;
    //销售额
    private double salesAmount;
    //销售平台
    private String platform;
    //销售客户端
    private String client;
    //订单时间
    private String orderTime;
    //销售量的单位
    private String unit;
    @Persistent
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //销售额单位
    private String salesAmountUnit;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAreaId() {
        return sellerAreaId;
    }

    public void setSellerAreaId(String sellerAreaId) {
        this.sellerAreaId = sellerAreaId;
    }

    public String getSellerAreaName() {
        return sellerAreaName;
    }

    public void setSellerAreaName(String sellerAreaName) {
        this.sellerAreaName = sellerAreaName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAreaName() {
        return buyerAreaName;
    }

    public void setBuyerAreaName(String buyerAreaName) {
        this.buyerAreaName = buyerAreaName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(double salesVolume) {
        this.salesVolume = salesVolume;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSalesAmountUnit() {
        return salesAmountUnit;
    }

    public void setSalesAmountUnit(String salesAmountUnit) {
        this.salesAmountUnit = salesAmountUnit;
    }




}