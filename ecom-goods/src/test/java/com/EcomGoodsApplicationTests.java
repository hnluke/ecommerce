package com;

import com.message.GoodsMessage;
import com.model.Goods;

import com.service.ICommentsService;
import com.service.IGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class EcomGoodsApplicationTests {
    @Resource
    IGoodsService goodsService;

    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    ICommentsService commentsService;

    @Test
    void contextLoads() {

    }

    @Test
    void sendMessage() {
        GoodsMessage message = new GoodsMessage();

        //rabbitTemplate.convertAndSend("ex.direct", "gd.or", message);

        rabbitTemplate.convertAndSend("ex.direct", "gd.or", message);
        //rabbitTemplate.convertAndSend("ex.direct", "gd.or", message);
    }


    @Test
    void findComments() {
        System.out.println(commentsService.findCommentUnionById(null));
    }

}
