package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.OrderDao;
import com.sifan.livegoods.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Order order) {
        mongoTemplate.save(order);
    }
}
