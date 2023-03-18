package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 热门推荐 - 商品数据访问实现。
 */
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询商品。
     * @param query
     * @return
     */
    @Override
    public List<Item> selectRecommendation(Query query) {
        return mongoTemplate.find(query, Item.class);
    }
}
