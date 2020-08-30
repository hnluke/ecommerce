package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.WatchEvent;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 定单实体类
 */
@Entity
public class Order implements Serializable {
    private static final long serialVersionUID = 1008L;
    @Id
    private String id;                      // 订单id
    @Column
    private Integer goodId;                 // 商品id
    @Column
    private String goodName;                // 商品名称
    @Column
    private Integer orderNum;               // 商品数量
    @Column
    private Double orderPrice;              // 商品价格
    @Column
    private String orderStatus;             // 订单状态("未支付", "已支付", "已派送", "已取消", "成功")
    @Column
    private String userName;                // 用户名
    @Column
    private String userAddr;                // 用户地址
    @Column
    private String userPhone;               // 用户电话
    @Column
    private Date orderDate;                 // 下单日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", orderNum=" + orderNum +
                ", orderPrice=" + orderPrice +
                ", orderStatus='" + orderStatus + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
