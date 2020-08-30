package com;

import com.model.Deliver;
import com.repository.DeliverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class EcomDeliverApplicationTests {
    @Resource
    private DeliverRepository deliverRepository;

    @Test
    void contextLoads() {
        Deliver deliver = new Deliver();
        deliver.setId("SF145224");
        deliver.setGoodName("手机");
        deliver.setDeliverAddr("湖南省益阳市赫山区");
        deliver.setDeliverPhone("1335452452");
        deliver.setOrderId("45244551");
        deliverRepository.save(deliver);
        System.out.println(deliverRepository.findAll());
    }

}
