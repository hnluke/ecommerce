package com.repository;

import com.model.Order;
import org.apache.http.util.Args;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByGoodNameLike(String goodName);

    List<Order> findByGoodName(String userName);

    Order findOrderById(String id);

    List<Order> findByOrOrderStatus(String orderStatus);










}
