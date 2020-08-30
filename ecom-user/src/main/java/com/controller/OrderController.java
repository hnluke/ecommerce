package com.controller;

import com.model.Order;
import com.model.Users;
import com.service.IUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    private final static String PROVIDE = "ecom-order/";
    @Resource
    RestTemplate restTemplate;
    @Resource
    IUsersService usersService;
    @GetMapping("/orders")
    public String showList(Map<String, Object> map) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "orders", List.class);
        map.put("orders", orders);
        return "userpages/orders/list";

    }

    @GetMapping("/orders/{userName}")
    public String showListByUserName(Map<String, Object> map,
                                     @PathVariable("userName") String userName) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "orders/"
                + userName, List.class);
        System.out.println("orders: " + orders);
        //System.out.println(orders);
        map.put("orders", orders);
        //map.put("message", "");
        return "userpages/orders/list";

    }

    // 支付订单
    @PutMapping("/order/{userName}/{id}")
    public String afford(Map<String, Object> map, @PathVariable("id") String id,
                         @PathVariable("userName") String userName) {
        Users users = usersService.findUserByUserName(userName);
        Order queryOrder = restTemplate.getForObject(
                "http://" + PROVIDE + "query/" + id, Order.class);
        if(users.getUserAccount() > queryOrder.getOrderPrice()) {
            List<Object> orders = restTemplate.getForObject(
                    "http://" + PROVIDE + "order/" + userName + "/" +id, List.class);
            map.put("message", "支付成功");
            map.put("orders", orders);
        }else {
            map.put("message", "余额不足");
            map.put("orders", queryOrder);
        }

        //if(users.getUserAccount() > ((Order)orders.get(0)).getOrderPrice())

        return "userpages/orders/list";

    }

    // 取消订单
    @DeleteMapping("/order/{id}")
    public String cancel(Map<String, Object> map, @PathVariable("id") String id) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "ordercancel/" + id, List.class);
        map.put("orders", orders);
        return "userpages/orders/list";

    }

    // 用户取消订单
    @PutMapping("/cancelOrder/{id}")
    public String cancelOrder(Map<String, Object> map, @PathVariable("id") String id) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "cancelOrder/" + id, List.class);
        map.put("orders",orders);
        return "userpages/orders/cancel";
    }

    // 进入取消订单的页面
    @GetMapping("/cancelOrder")
    public String showCancelOrder(Map<String, Object> map) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "cancelOrder", List.class);
        map.put("orders",orders);
        return "userpages/orders/cancel";
    }

    // 进入确认收货的页面
    @GetMapping("/takes/{userName}")
    public String showTakes(Map<String, Object> map,
                            @PathVariable("userName") String userName) {
//        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "takes/" + userName, List.class);
//        map.put("orders",orders);
        return "userpages/orders/takegoods";
    }

    // 用户点击确认收货
    @PutMapping("/take/{id}")
    public String takeGoods(Map<String, Object> map,
                            @PathVariable("id") String id) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "take/" + id, List.class);
        map.put("orders",orders);
        return "userpages/orders/takegoods";
    }

    // 进入商户订单配送页面
    @GetMapping("/orderDeliver")
    public String showOrderDeliver(Map<String, Object> map) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "orderDeliver", List.class);
        map.put("orders", orders);
        return "commpages/orders/orderdeliver";
    }

    // 商户配送订单
    @PutMapping("/orderDeliver/{id}")
    public String orderDeliver(Map<String, Object> map, @PathVariable("id") String id) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "orderDeliver/" + id, List.class);
        map.put("orders",orders);
        return "commpages/orders/orderdeliver";
    }

    @PutMapping("/commCancel/{id}")
    public String commCancel(Map<String, Object> map, @PathVariable("id") String id) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "commCancel/" + id, List.class);
        return "commpages/orders/ordercancel";
    }

    @GetMapping("/commCancel")
    public String showCancel(Map<String, Object> map) {
        List<Object> orders = restTemplate.getForObject("http://" + PROVIDE + "commCancel", List.class);
        return "commpages/orders/ordercancel";
    }






}
