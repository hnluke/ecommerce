package com.sevice;

import com.model.Deliver;
import com.message.OrderMessage;

import java.util.List;

public interface IDeliverService {

    /**
     * 新增物流派送单
     * @param message   依据定单提供的消息来产生物流单
     */
    public void insertDeliver(OrderMessage message);

    /**
     * 删除物流派送单
     * @param id
     */
    public void deleteDeliver(String id);

    /**
     * 修改物流状态
     * @param id
     */
    public void updateDeliver(String id, String deliverStatus);

    /**
     * 依据物流单号查询
     * @param id
     * @return
     */
    public Deliver findByDeliverId(String id);

    /**
     * 查询所有的物流单号
     * @return
     */
    public List<Deliver> findAll();

    /**
     * 查询匹配物流状态的物流单
     * @param deliverStatus
     * @return
     */
    public List<Deliver> findDeliverByStatus(String deliverStatus);

    /**
     * 根据orderId 来修改物流单状态
     * @param id
     * @param deliverStatus
     */
    public void updateDeliverByOrderId(String id, String deliverStatus);



}
