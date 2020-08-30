package com.service.impl;

import com.model.Goods;
import com.service.AsyncDemo;
import com.service.IGoodsService;
import com.mapper.GoodsMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    GoodsMapper goodsMapper;
    @Resource
    AsyncDemo asyncDemo;
     //查询商品
    @Override
    @Cacheable(value="gCache")
    public List<Goods> findGood(Integer goodId, String goodStatus) {
        //List<Goods> goodsList = goodsMapper.findGood(goodId, goodStatus);
        List<Goods> goodsList = goodsMapper.findGood(null, null);
        return goodsList;
    }


    // 上架商品
    @Override
    @CachePut(value="gCache", key = "#goods.goodId")
    public boolean insertGood(Goods goods) {
        return goodsMapper.insertGood(goods);
    }

    // 下架商品
    @Override
    @CacheEvict(value="gCache", key = "#goodId")
    public boolean deleteGood(Integer goodId) {
        return goodsMapper.deleteGood(goodId);
    }

    // 更新商品信息
    @Override
    @CachePut(value="gCache", key = "#result.goodId")
    public Goods updateGood(Goods goods) {
        goodsMapper.updateGood(goods);
        return goods;
    }

    // 更新商品库存
    @Override
    @CachePut(value="gCache", key = "#Id")
    public boolean updateGoodStore(Integer id, Integer buyNum) {
        return goodsMapper.updateGoodNumber(id, buyNum);

    }

     //购买商品
    @Override
    //@CachePut(value="gCache", key = "#id")
    public void buyGood(Integer id, Integer buyNum, String user) {
        goodsMapper.updateGoodNumber(id, buyNum);
        // 发送消息给订单模块以创建订单
        asyncDemo.sendMessage(id, buyNum, user);

    }
    // 购买商品
//    @Override
//    @CachePut(value="gcache", key = "#id")
//    public List<Goods> buyGood(Goods goods, Integer id, Integer buyNum, String user) {
//        goodsMapper.updateGoodNumber(goods);
//        // 发送消息给订单模块以创建订单
//        asyncDemo.sendMessage(id, buyNum, user);
//        List<Goods> goodsList = goodsMapper.findGood();
//        return goodsList;
//
//    }
}
