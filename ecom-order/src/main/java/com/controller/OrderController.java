package com.controller;

import com.model.Order;
import com.service.AsyncDemo;
import com.service.IOrderService;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController {
    @Resource
    IOrderService orderService;
    @Resource
    AsyncDemo asyncDemo;
    // 返回所有的订单
    @RequestMapping("/orders")
    public List<Order> showOrders() {
        List<Order> orderList = orderService.findAll();
        return orderList;
    }

    // 用户模块"支付订单", 返回支付页面的数据(只显示未支付的订单)
    @RequestMapping("/orders/{userName}")
    public List<Order> showOrderByUserAndStaus(@PathVariable("userName") String userName) {
        List<Order> orderList = orderService.findByStatusAndUser(userName, "未支付");
        return orderList;
    }

    // 查询指定订单编号订单数据
    @RequestMapping("/query/{id}")
    public Order showOrderById(@PathVariable("id") String id) {
        Order orderList = orderService.findByOrderId(id);
        return orderList;
    }

    // 返回指定订单状态的订单
//    @RequestMapping("/orders/{orderStatus}")
//    public List<Order> showOrderByStatus(@PathVariable("orderStatus") String orderStatus) {
//        List<Order> orderList = orderService.findByOrderByStatus(orderStatus);
//        return orderList;
//    }



    // 支付订单
    @RequestMapping("/order/{userName}/{id}")
    public List<Order> afford(@PathVariable("id") String id,
                              @PathVariable("userName") String userName) {
        //asyncDemo.sendMessage(id, userName,userAddr, userPhone);
        asyncDemo.sendMessage(id, "ex.topic", "order.#", "已支付", "订单支付");
        List<Order> orderList = orderService.findByStatusAndUser(userName, "未支付");
        return orderList;
    }

    // 用户取消订单
    @RequestMapping("/cancelOrder/{id}")
    public List<Order> cancel(@PathVariable("id") String id) {
        // 更新订单的状态
        //orderService.updateOrder(id, "已取消");
        Order order = orderService.findByOrderId(id);
        if("未支付".equals(order.getOrderStatus())) {
            asyncDemo.sendMessage(id, "ex.topic", "order.#", "订单取消", "未付取消");
        }else if("商户取消".equals(order.getOrderStatus())) {
            asyncDemo.sendMessage(id, "ex.topic", "order.#", "订单取消", "已付取消");
        }
        // 向"order.de, order.gd, order.usr"发送信息

        // 用户的取消订单列表中只能包含"未支付"或"已退回"的订单
        List<Order> orderList = orderService.findByStatus("未支付", "商户取消");
        return orderList;
    }

    // 进入用户取消订单页面
    @RequestMapping("/cancelOrder")
    public List<Order> cancelOrder() {
        // 用户的取消订单列表中只能包含"未支付"或"已退回"的订单
        List<Order> orderList = orderService.findByStatus("未支付", "商户取消");
        return orderList;
    }

    // 订单派送
    @RequestMapping("/orderDeliver/{id}")
    public List<Order> orderDeliver(@PathVariable("id") String id) {
        orderService.updateOrder(id, "已配送");
        asyncDemo.sendMessage(id, "ex.topic", "order.#", "已配送", "订单配送");
        List<Order> orderList = orderService.findByOrderByStatus("已支付");
        return orderList;
    }

    // 返回已支付的订单
    @RequestMapping("/orderDeliver")
    public List<Order> showAffordOrder() {
        List<Order> orderList = orderService.findByOrderByStatus("已支付");
        return orderList;
    }


    // 商家取消订单,并返回用户退回的订单
    @RequestMapping("/commCancel/{id}")
    public List<Order> commCancel(@PathVariable("id") String id) {
        asyncDemo.sendMessage(id, "ex.topic", "order.#", "商户取消", "订单取消");
        List<Order> orderList = orderService.findByOrderByStatus("用户退回");
        return orderList;
    }

    // 商家取消订单,并返回用户退回的订单
    @RequestMapping("/commCancel")
    public List<Order> showBack() {
        List<Order> orderList = orderService.findByOrderByStatus("用户退回");
        return orderList;
    }

    // 用户点击确认收货返回页面所需的数据
    @RequestMapping("/takes/{userName}")
    public List<Order> showTakes(@PathVariable("userName") String userName) {
        List<Order> orderList = orderService.findByStatusAndUser(userName, "派送中");
        return orderList;
    }

    // 用户确认收货
    @RequestMapping("/take/{id}")
    public List<Order> tackGoods(@PathVariable("id") String id) {
        orderService.updateOrder(id, "订单完成");
        asyncDemo.sendMessage(id, "ex.direct","or.dr", "订单完成", "用户收货");
        List<Order> orderList = orderService.findByOrderByStatus("派送中");
        return orderList;
    }

}
