package com.sevice.impl;

import com.model.Deliver;
import com.message.OrderMessage;
import com.repository.DeliverRepository;
import com.sevice.IDeliverService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DeliverServiceImpl implements IDeliverService {

    @Resource
    DeliverRepository deliverRepository;
    @Resource
    MongoTemplate mongoTemplate;

    /**
     *  依据定单提供的消息来产生物流单
     * @param message
     */
    @Override
    public void insertDeliver(OrderMessage message) {
        Deliver deliver = new Deliver();
        deliver.setId(getDeliverNo());
        deliver.setOrderId(message.getOrder().getId());
        deliver.setGoodName(message.getOrder().getGoodName());
        deliver.setDeliverName(message.getOrder().getUserName());
        deliver.setDeliverPhone(message.getOrder().getUserPhone());
        deliver.setGoodPrice(message.getOrder().getOrderPrice());
        deliver.setNum(message.getOrder().getOrderNum());
        //deliver.setGoodCommon("");
        deliver.setDeliverStatus("未派送");
        deliver.setDeliverAddr(message.getOrder().getUserAddr());
        deliverRepository.save(deliver);
        


    }

    /**
     * 删除物流单
     * @param id
     */

    @Override
    public void deleteDeliver(String id) {
        deliverRepository.deleteById(id);
    }

    /**
     * 修改物流状态
     * @param id
     * @param deliverStatus
     */
    @Override
    public void updateDeliver(String id, String deliverStatus) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update = Update.update("deliverStatus", deliverStatus);
        mongoTemplate.updateFirst(query, update, Deliver.class);
    }

    @Override
    public void updateDeliverByOrderId(String id, String deliverStatus) {
        Query query=new Query(Criteria.where("orderId").is(id));
        Update update = Update.update("deliverStatus", deliverStatus);
        mongoTemplate.updateFirst(query, update, Deliver.class);
    }


    /**
     * 查询物流单据
     * @param id
     * @return
     */
    @Override
    public Deliver findByDeliverId(String id) {
        return deliverRepository.findDeliverById(id);
    }

    /**
     * 查询所有的物流单据
     * @return
     */

    @Override
    public List<Deliver> findAll() {
        return deliverRepository.findAll();
    }

    public List<Deliver> findDeliverByStatus(String deliverStatus) {
        return deliverRepository.findByDeliverStatus(deliverStatus);
    }

    /**
     * 产生物流单号
     * @return
     */
    public String getDeliverNo(){
        String id="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        int random=(int) (Math.random()*100);
        String randStr = String.format("%03d", random);
        id = "SF" + dateStr + randStr;
        return id;
    }

}
