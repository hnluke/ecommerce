package com;

import com.message.GoodsMessage;
import com.model.Order;
import com.repository.OrderRepository;
import com.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class EcomOrderApplicationTests {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    IOrderService orderService;

    @Resource
    private OrderRepository orderRepository;
    @Test
    void contextLoads() throws ParseException {
        Order order = new Order();
        order.setId("1452574799");
        order.setGoodName("网球");
        order.setOrderNum(50);
        order.setOrderPrice(6000.0D);
        order.setOrderStatus("下单未确认");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());



        //orderRepository.save(order);
        //userRepository.save(userData);

       // orderRepository.save(order);
        System.out.println(orderRepository.findAll());

    }

    @Test
    public void updateOrder() {
        orderService.updateOrder("1452574588", "下单已确认");
        System.out.println("更新成功!");
    }

    @Test
    void getData() {
        List<Order> orderList = orderRepository.findAll();
        for(Order order : orderList) {
            System.out.println(order);
        }
    }

    @Test
    public void reciveGdOr() {
        GoodsMessage message = (GoodsMessage)rabbitTemplate.receiveAndConvert("gd.or");
        System.out.println(message);
    }

//    @Test
////    public void getOrderNo(){
////        String id="";
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
////        String temp = sdf.format(new Date());
////        int random=(int) (Math.random()*100);
////        id = temp + random;
////        String str = String.format("%17s", id);
////        System.out.println(str);
////    }

    @Test
    public void findById() {
        System.out.println(orderRepository.findById("1452574777"));
    }

    @Test
    public void deleteById() {
        orderRepository.deleteById("14525747");
    }
    @Test
    public void getOrderNo(){
        String id="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        int random=(int) (Math.random()*100);
        String randStr = String.format("%03d", random);
        id = dateStr + randStr;
        System.out.println(id);
    }

    @Test
    public void getOrderStatus() {
        System.out.println(orderService.findByStatus("已取消", "未支付", "已配送"));
    }

    @Test
    public void showDate() {
        //System.out.println(new Date());
        Order order = new Order();
        order.setOrderDate(new Date());
        System.out.println(order.getOrderDate());
    }

    @Test
    public void findUserAndStatus() {
        System.out.println(orderService.findByStatusAndUser("luke", "派送中"));
    }

}
