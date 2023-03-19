package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.OrderDao;
import com.sifan.livegoods.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

// 订单数据访问实现
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateCommentState(String orderId, int commentState) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));
        Update update = Update.update("commentState", commentState);
        mongoTemplate.updateFirst(query, update, Order.class);
    }

    @Override
    public Order findById(String orderId) {
        return mongoTemplate.findById(orderId, Order.class);
    }
}
