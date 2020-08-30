package com.repository;

import com.model.Deliver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeliverRepository extends MongoRepository<Deliver, String> {

    public List<Deliver> findByOrderId(String orderId);

    public List<Deliver> findByGoodName(String goodName);

    // 查询符号物流状态的物流单
    public List<Deliver> findByDeliverStatus(String deliverStatus);

    // 依据物流编号查询物流单
    public Deliver findDeliverById(String id);

}
