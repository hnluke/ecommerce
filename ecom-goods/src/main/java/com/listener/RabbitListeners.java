package com.listener;

import com.message.DeliverMessage;
import com.message.OrderMessage;
import com.service.IGoodsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitListeners {
    @Resource
    IGoodsService goodsService;
//    @RabbitListener(queues = "deliver.gd")
//    public void recieveDeliver(DeliverMessage message) {
//        if("投递退回".equals(message.getMessDeli())) {
//
//        }
//    }

    // 监听从订单模块发过来的消息，如果是订单取消，或是订单退回，则更新库存.监听队列为order.gd
    @RabbitListener(queues = "order.gd")
    public void recieveOrder(OrderMessage message) {
        if("订单取消".equals(message.getMessName()) ||
            "订单退回".equals(message.getMessName())) {
            goodsService.updateGoodStore(message.getOrder().getGoodId(),
                    message.getOrder().getOrderNum() * (-1));
        }
    }

    @RabbitListener(queues = "deliver.gd")
    public void recieveDeliver(DeliverMessage message) {

    }

//    @RabbitListener(queues = "usr.gd")
//    public void recieveUser(UserMessage message) {
//
//    }
}
