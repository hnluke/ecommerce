package com.controller;

import com.google.gson.Gson;
import com.model.Comments;
import com.model.Goods;
import com.service.ICommentsService;
import com.service.IGoodsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {
    @Resource
    IGoodsService goodsService;
    @Resource
    ICommentsService commentsService;

    // 查询所有的上架商品
    @RequestMapping("/goods")
    public List<Goods> showList() {
        List<Goods> goodsList = goodsService.findGood(null,null);
//        Gson gson = new Gson();
//        String goodJsonStr = gson.toJson(goodsList);
        return goodsList;
    }

    // 查看评论
    @RequestMapping("/comments")
    public List<Comments> showComments () {
        List<Comments> commentsList = commentsService.findCommentUnionById(null);
        return commentsList;
    }

    // 购买商品
    @RequestMapping("good/{id}/{user}")
    public List<Goods> updateGood(@PathVariable("id") Integer id,
                                  @PathVariable("user") String user) {

        Integer num = 1;
        //List<Goods> goods = goodsService.findGood(id, null);
        //Goods good = goods.get(0);
        Goods good = new Goods();
        good.setGoodId(id);
        good.setGoodStore(1);
        goodsService.buyGood(id, num, user);
        List<Goods> goodsList = goodsService.findGood(null, null);
        return goodsList;
    }

    // 上架商品
    @RequestMapping("/addGoods")
    public List<Goods> addGoods(Goods goods) {
        goodsService.insertGood(goods);
        List<Goods> goodsList = goodsService.findGood(null, null);
        //System.out.println("goodList: " + goodsList);
        return goodsList;
    }

    // 下架商品
    @RequestMapping("/deleteGood/{id}")
    public List<Goods> deleteGood(@PathVariable("id") Integer id) {
        goodsService.deleteGood(id);
        List<Goods> goodsList = goodsService.findGood(null, null);
        return goodsList;
    }
}
