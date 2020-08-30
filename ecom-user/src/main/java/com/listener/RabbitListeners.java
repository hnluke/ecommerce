package com.listener;

import com.message.DeliverMessage;
import com.message.OrderMessage;
import com.model.Users;
import com.service.IUsersService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitListeners {

    @Resource
    IUsersService usersService;

    // 监听从订单模块发过来的信息, 队列名order.usr
    @RabbitListener(queues = "order.usr")
    public void recieveOrder(OrderMessage message) {
        if(message != null) {
            Users users = new Users();
            users.setUserName(message.getOrder().getUserName());
            users.setUserAccount(0.0);
            if("订单退回".equals(message.getMessName()) ||
                "未付取消".equals(message.getMessName()) ||
                "已付取消".equals(message.getMessName())) {
                // 增加用户余额
                users.setUserAccount(message.getOrder().getOrderPrice() * (-1));
            }else if("订单支付".equals(message.getMessName())) {
                // 减去用户余额
                users.setUserAccount(message.getOrder().getOrderPrice());

            }
            usersService.updateUserBalance(users);
        }

    }

    // 监听从物流模块发过来的消息, 队列是deliver.usr
    @RabbitListener(queues = "deliver.usr")
    public void recieveDeliver(DeliverMessage message) {

    }

//    @Resource
//    IGoodsService goodsService;
////    @RabbitListener(queues = "deliver.gd")
////    public void recieveDeliver(DeliverMessage message) {
////        if("投递退回".equals(message.getMessDeli())) {
////
////        }
////    }
//
//    @RabbitListener(queues = "order.gd")
//    public void recieveOrder(OrderMessage message) {
//        if("订单取消".equals(message.getMessName())) {
//            goodsService.updateGoodStore(message.getGoodId(), message.getOrderNumber());
//        }
//        System.out.println("收到订单模块消息: " + message);
//    }
}
