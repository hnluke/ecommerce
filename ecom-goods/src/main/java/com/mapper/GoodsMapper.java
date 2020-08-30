package com.mapper;

import com.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GoodsMapper {


    public List<Goods> findGood(Integer goodId, String goodStatus);


    /**
     * 上架商品
     * @param goods
     * @return
     */
    public boolean insertGood(Goods goods);

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    public boolean updateGood(Goods goods);


    /**
     *
     * @return
     */
    public boolean updateGoodNumber(@Param("goodId") Integer goodId,
                                    @Param("goodStore") Integer goodStore);



    /**
     * 下架商品
     * @param goodId
     * @return
     */
    public boolean deleteGood(Integer goodId);

}
