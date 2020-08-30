package com.listener;

import com.message.DeliverMessage;
import com.message.GoodsMessage;
import com.service.IOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitListeners {
    @Resource
    IOrderService orderService;

    // 监听从商品模块发过来的消息，一旦收到"下单"信息，则生成订单
    @RabbitListener(queues = "gd.or")
    public void recieveGoods(GoodsMessage message) {
        if(message != null) {
            if("下单".equals(message.getMessName())) {
                orderService.insertOrder(message);
            }
            System.out.println(message);
        }


    }

    @RabbitListener(queues = "deliver.or")
    public void recieveDeliver(DeliverMessage message) {
        if(message != null) {
            if("用户退回".equals(message.getMessDeli())) {
                orderService.updateOrder(message.getDeliver().getId(), "用户退回");
            }else if("派送中".equals(message.getMessDeli())) {
                orderService.updateOrder(message.getDeliver().getId(), "派送中");
            }
        }

    }
}
