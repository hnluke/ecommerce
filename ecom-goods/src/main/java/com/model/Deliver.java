package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Deliver implements Serializable {
    public final static long serialVersionUID = 1003L;
    @Id
    private String id;                  // 物流单号
    @Column
    private String orderId;             // 订单号
    @Column
    private String goodName;            // 商品名称
    @Column
    private Integer num;                // 数量
    @Column
    private Double goodPrice;           // 订单总价
    @Column
    private String deliverName;         // 收件人姓名
    @Column
    private String deliverAddr;         // 投递地址
    @Column
    private String deliverPhone;        // 联系人电话
    @Column
    private String goodCommon;          // 商品评价
    @Column
    private String deliverStatus;       // 物流状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverAddr() {
        return deliverAddr;
    }

    public void setDeliverAddr(String deliverAddr) {
        this.deliverAddr = deliverAddr;
    }

    public String getDeliverPhone() {
        return deliverPhone;
    }

    public void setDeliverPhone(String deliverPhone) {
        this.deliverPhone = deliverPhone;
    }

    public String getGoodCommon() {
        return goodCommon;
    }

    public void setGoodCommon(String goodCommon) {
        this.goodCommon = goodCommon;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Deliver{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", goodName='" + goodName + '\'' +
                ", num=" + num +
                ", goodPrice=" + goodPrice +
                ", deliverName='" + deliverName + '\'' +
                ", deliverAddr='" + deliverAddr + '\'' +
                ", deliverPhone='" + deliverPhone + '\'' +
                ", goodCommon='" + goodCommon + '\'' +
                ", deliverStatus='" + deliverStatus + '\'' +
                '}';
    }
}
