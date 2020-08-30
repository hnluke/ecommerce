package com.service;

import com.model.Goods;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IGoodsService {

    // 依据商品id查找商品

    public List<Goods> findGood(Integer goodId, String goodStatus);


    // 上架商品

    public boolean insertGood(Goods goods);

    // 下架商品

    public boolean deleteGood(Integer goodId);

    // 更新商品信息
    public Goods updateGood(Goods goods);

    // 更新商品库存
    //public boolean updateGoodStore(Integer goodId, Integer goodStore);

    public boolean updateGoodStore(Integer id, Integer buyNum);

    // 购买商品
    public void buyGood(Integer id, Integer buyNum, String user);


}
