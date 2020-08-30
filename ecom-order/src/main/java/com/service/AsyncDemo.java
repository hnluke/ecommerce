package com.service;

import com.model.Order;
import com.message.OrderMessage;
import com.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AsyncDemo {
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    OrderRepository orderRepository;
    @Resource
    IOrderService orderService;


    // 商户在订单上按下"配送"后，向物流模块发送的信息
//    @Async
//    public void sendMessage(String id) {
//        Order order = orderRepository.findOrderById(id);
//        if(order != null) {
//            orderService.updateOrder(id, "已配送");
//            OrderMessage message = new OrderMessage();
//            message.setOrder(order);
//            message.setMessName("订单配送");
//            rabbitTemplate.convertAndSend("ex.direct", "or.dr", message);
//        }
//    }

//    // 用户在订单上按下"支付"后，向用户模块， 物流模块和商品模块同时发送信息
//    @Async
//    public void sendAffordMessage(String id) {
//        Order order = orderRepository.findOrderById(id);
//        if(order != null) {
//            OrderMessage message = new OrderMessage();
//            message.setOrder(order);
//            message.setMessName("订单支付");
//            rabbitTemplate.convertAndSend("ex.topic", "order.#", message);
//        }
//    }
//
//
//    // 用户在订单上按下"取消"后，向用户模块，物流模块和商品模块同时发送信息"订单取消"
//    @Async
//    public void sendCancelMessage(String id) {
//        Order order = orderRepository.findOrderById(id);
//        if(order != null) {
//            OrderMessage message = new OrderMessage();
//            message.setOrder(order);
//            message.setMessName("订单取消");
//            rabbitTemplate.convertAndSend("ex.topic", "order.#", message);
//        }
//    }
//
//    // 商户在订单上按下"取消订单"后，向用户模块，物流模块和商品模块同时发送信息"订单退回"
//    @Async
//    public void sendCommCancelMessage(String id) {
//        Order order = orderRepository.findOrderById(id);
//        if(order != null) {
//            OrderMessage message = new OrderMessage();
//            message.setOrder(order);
//            message.setMessName("订单退回");
//            rabbitTemplate.convertAndSend("ex.topic", "order.#", message);
//        }
//    }

    @Async
    public void sendMessage(String id, String exchange,
                                     String queue, String orderStatus,
                                     String messName) {
        Order order = orderRepository.findOrderById(id);
        if(order != null) {
            orderService.updateOrder(id, orderStatus);
            OrderMessage message = new OrderMessage();
            message.setOrder(order);
            message.setMessName(messName);
            rabbitTemplate.convertAndSend(exchange, queue, message);
        }
    }



}
