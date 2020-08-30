package com.controller;

import com.model.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {
    private final static String PROVIDE = "ecom-goods/";
    @Resource
    RestTemplate restTemplate;
    // 显示所有商品
    @GetMapping("/goods")
    public String showList(Map<String, Object> map) {
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "goods", List.class);
        map.put("goods", goods);
        return "userpages/goods/list";

    }

    // 进入购买页面
    @GetMapping("/buy")
    public String buyGoods(Map<String, Object> map) {
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "goods", List.class);
        map.put("goods", goods);
        return "userpages/goods/buy";

    }

    // 显示商品评价页面
    @GetMapping("/comments")
    public String comments(Map<String, Object> map) {
        List<Object> comments = restTemplate.getForObject("http://" + PROVIDE + "comments", List.class);
        map.put("comments", comments);
        System.out.println(comments);
        return "userpages/goods/comment";
    }


    // 购买商品, 同时把当前用户名传递到商品模块
    @PutMapping("/good/{id}/{user}")
    public String updateGood(@PathVariable("id") Integer id, @PathVariable("user") String user, Map<String, Object> map) {
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "good/" + id + "/" + user, List.class);
        map.put("goods", goods);
        //System.out.println(user);
        return "userpages/goods/buy";
    }

    // 进入商品上架页面
    @GetMapping("/good")
    public String showAddGood(Map<String, Object> map) {
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "goods", List.class);
        map.put("goods", goods);
        return "commpages/goods/insert";
    }

    // 上架商品，同时回到新增页面
    @PostMapping("/addgood")
    public String addGood(Map<String, Object> map, Goods good) {
        String suffix = "?goodName=" + good.getGoodName() + "&goodType=" + good.getGoodType()
                + "&goodModel=" + good.getGoodModel() + "&goodProduct=" + good.getGoodProduct()
                + "&goodPrice=" + good.getGoodPrice() + "&goodStore=" + good.getGoodStore()
                + "&goodStatus=上架";
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "addGoods" + suffix, List.class);
        map.put("goods", goods);
        return "commpages/goods/insert";
    }


    // 进入商品下架页面
    @GetMapping("/delete")
    public String showDelete(Map<String, Object> map) {
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "goods", List.class);
        map.put("goods", goods);
        return "commpages/goods/delete";
    }

    // 商品下架
    @DeleteMapping("/delete/{id}")
    public String showDelete(Map<String, Object> map, @PathVariable("id") Integer id) {
        List<Object> goods = restTemplate.getForObject("http://" + PROVIDE + "deleteGood/" + id, List.class);
        map.put("goods", goods);
        return "commpages/goods/delete";
    }
}
