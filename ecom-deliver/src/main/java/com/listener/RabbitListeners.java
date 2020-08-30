package com.listener;

import com.message.OrderMessage;
import com.sevice.IDeliverService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitListeners {
    @Resource
    IDeliverService deliverService;

    // 从or.dr队列中接收"订单配送"的信息，来产生物流单据
    @RabbitListener(queues = "or.dr")
    public void recieveGoods(OrderMessage message) {
        if("订单配送".equals(message.getMessName())) {
            // 产生物流单号
            deliverService.insertDeliver(message);
        }else if("用户收货".equals(message.getMessName())){
            String orderId = message.getOrder().getId();
            deliverService.updateDeliverByOrderId(orderId, "派送完成");

        }

    }

    // 从order.de里接收其它信息
    @RabbitListener(queues = "order.de")
    public void recieveOrder(OrderMessage message) {

    }
}
