package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

// 查询商品详情数据访问实现
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 主键查询商品
     *
     * @param id
     * @return
     */
    @Override
    public Item findItemById(String id) {
        return mongoTemplate.findById(id, Item.class);
    }
}
