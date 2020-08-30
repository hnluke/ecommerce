package com.service;

import com.message.GoodsMessage;
import com.model.Order;

import java.util.List;

public interface IOrderService {
    /**
     * 新增订单
     */
    public void insertOrder(GoodsMessage message);

    /**
     * 修改订单
     */
    public void updateOrder(String id, String orderStatus);

    /**
     * 依据订单编号查询
     * @param id
     * @return
     */
    public Order findByOrderId(String id);

    /**
     * 查询所有订单
     * @return
     */
    public List<Order> findAll();

    /**
     * 删除定单
     * @param id
     */

    public void deleteOrder(String id);

    /**
     * 查找指定订单状态的订单
     * @param orderStatus
     * @return
     */
    public List<Order> findByOrderByStatus(String orderStatus);

    /**
     * 查找订单状态在指定范围的订单
     * @param str
     * @return
     */
    public List<Order> findByStatus(String... str);

    // 查找到与用户匹配的订单
    public List<Order> findByStatusAndUser(String userName, String... str);
}
