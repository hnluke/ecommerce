package com.service.impl;

import com.message.GoodsMessage;
import com.model.Order;
import com.repository.OrderRepository;
import com.service.IOrderService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    OrderRepository orderRepository;
    @Resource
    MongoTemplate mongoTemplate;

    /**
     * 新增订单
     * @param message
     */
    @Override
    public void insertOrder(GoodsMessage message) {
        Order order = new Order();
        order.setGoodId(message.getGood().getGoodId());
        order.setGoodName(message.getGood().getGoodName());
        order.setId(getOrderNo());
        order.setOrderNum(message.getBuyNum());
        order.setOrderPrice(message.getGood().getGoodPrice() * message.getBuyNum());
        order.setOrderStatus("未支付");
        // 让系统时间加8小时，以解决MongoDB的UTC时区的问题
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 8);
        Date date = cal.getTime();
        order.setOrderDate(date);
        order.setUserName(message.getUsers().getUserName());
        order.setUserAddr(message.getUsers().getUserAddr());
        order.setUserPhone(message.getUsers().getUserPhone());
        //mongoTemplate.save(order);
        orderRepository.save(order);

    }

    /**
     * 修改订单状态
     * @param id
     * @param orderStatus
     */
    @Override
    public void updateOrder(String id, String orderStatus) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update = Update.update("orderStatus", orderStatus);
        mongoTemplate.updateFirst(query, update, Order.class);
    }

    /**
     * 依据定单编号查询
     * @param id
     * @return
     */
    @Override
    public Order findByOrderId(String id) {
        return orderRepository.findOrderById(id);
    }

    public List<Order> findByOrderByStatus(String orderStatus) {
        return orderRepository.findByOrOrderStatus(orderStatus);
    }

    // 查询订单状态在指定范围里的定单
    public List<Order> findByStatus(String... str) {
        List<Order> orderList = mongoTemplate.find (
                new Query(Criteria.where("orderStatus").in(str)),Order.class);
        return orderList;
    }

    public List<Order> findByStatusAndUser(String userName, String... str) {
        List<Order> orderList = mongoTemplate.find (
                new Query(Criteria.where("orderStatus").in(str).and("userName").is(userName)),Order.class);
        return orderList;
    }




    /**
     * 查询所有订单
     * @return
     */
    @Override
    public List<Order> findAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    /**
     * 删除编号为id的订单
     * @param id
     */
    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);

    }



    /**
     * 产生订单编号(17位， 根据时间年月日时分秒+3位100以内的随机数)
     * @return
     */
    public String getOrderNo(){
        String id="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        int random=(int) (Math.random()*100);
        String randStr = String.format("%03d", random);
        id = dateStr + randStr;
        return id;
    }


}
