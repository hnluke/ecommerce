package com.sevice;

import com.message.DeliverMessage;
import com.model.Deliver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AsyncDemo {
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    IDeliverService deliverService;


    /**
     * 用户购买生成下单的消息推送
     * @param id
     */
    @Async
    public void sendMessage(String id, String messName) {
        Deliver deliver = deliverService.findByDeliverId(id);
        if(deliver!=null) {
            DeliverMessage message = new DeliverMessage();
            message.setMessDeli(messName);
            message.setDeliver(deliver);
            rabbitTemplate.convertAndSend("ex.topic", "deliver.#", message);
        }

    }
}
