package com.model;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
public class Goods implements Serializable {
    public final static long serialVersionUID = 1001L;

    private Integer goodId;             // 商品id
    private String goodName;            // 商品名称
    private String goodType;            // 商品类型
    private String goodModel;           // 商品型号
    private String goodProduct;         // 商品产地
    private Integer goodStore;          // 库存数量
    private String goodStatus;          // 商品状态(保留)
    private Double goodPrice;           // 商品价格
    private Date goodPutUpDate;         // 上架日期
    private String goodDetail;          // 商品详情

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getGoodModel() {
        return goodModel;
    }

    public void setGoodModel(String goodModel) {
        this.goodModel = goodModel;
    }

    public String getGoodProduct() {
        return goodProduct;
    }

    public void setGoodProduct(String goodProduct) {
        this.goodProduct = goodProduct;
    }

    public Integer getGoodStore() {
        return goodStore;
    }

    public void setGoodStore(Integer goodStore) {
        this.goodStore = goodStore;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(String goodDetail) {
        this.goodDetail = goodDetail;
    }


    public Date getGoodPutUpDate() {
        return goodPutUpDate;
    }

    public void setGoodPutUpDate(Date goodPutUpDate) {
        this.goodPutUpDate = goodPutUpDate;
    }

    public String getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(String goodStatus) {
        this.goodStatus = goodStatus;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", goodType='" + goodType + '\'' +
                ", goodModel='" + goodModel + '\'' +
                ", goodProduct='" + goodProduct + '\'' +
                ", goodStore=" + goodStore +
                ", goodStatus='" + goodStatus + '\'' +
                ", goodPrice=" + goodPrice +
                ", goodPutUpDate=" + goodPutUpDate +
                ", goodDetail='" + goodDetail + '\'' +
                '}';
    }
}
