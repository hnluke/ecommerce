package com.message;

import com.model.Order;

import java.io.Serializable;

public class OrderMessage implements Serializable {
    public final static long serialVersionUID = 1006L;

    private String messName;        // 消息名称："订单配送", "订单取消"
    private Order order;
//    private Integer goodId;         // 商品Id
//    private String goodName;        // 商品名称
//    private String userName;        // 用户名
//    private String orderId;         // 定单号
//    private Double orderAccount;     // 定单金额
//    private String userPhone;       // 用户电话号码
//    private String userAddr;        // 用户地址
//    private Integer orderNumber;    // 定单数量

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "messName='" + messName + '\'' +
                ", order=" + order +
                '}';
    }
}
