package com.service;

import com.mapper.GoodsMapper;
import com.mapper.UsersMapper;
import com.model.Goods;
import com.message.GoodsMessage;
import com.model.Users;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AsyncDemo {
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    GoodsMapper goodsMapper;
    @Resource
    UsersMapper usersMapper;

    /**
     * 用户购买生成下单的消息推送
     * @param id
     * @param buyNum
     */
    @Async
    public void sendMessage(Integer id, Integer buyNum, String userName) {
        List<Goods> goods = goodsMapper.findGood(id, null);
        // 查询出下单用户的信息
        Users users = usersMapper.findUserByUserName(userName);
        if(goods != null || goods.size() > 0) {
            GoodsMessage message = new GoodsMessage();
            Goods good = goods.get(0);
            message.setGood(good);
            message.setUsers(users);
            message.setBuyNum(buyNum);
            message.setMessName("下单");
            rabbitTemplate.convertAndSend("ex.direct", "gd.or", message);
        }

    }
}
