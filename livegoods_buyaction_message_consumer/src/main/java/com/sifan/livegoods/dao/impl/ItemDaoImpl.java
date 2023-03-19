package com.sifan.livegoods.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

// 商品数据访问实现
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 更新商品是否已出租字段。
    // 更新MongoDB中item集合的语法
    // db.item.update({},{"$set":{"rented":false}}, false, true);
    @Override
    public long update(String id, Boolean rented) {
        Query query = new Query();
        // 更新条件
        query.addCriteria(Criteria.where("id").is(id));
        // 更新内容
        Update update = Update.update("isRented", rented);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
        return result.getModifiedCount();
    }
}
