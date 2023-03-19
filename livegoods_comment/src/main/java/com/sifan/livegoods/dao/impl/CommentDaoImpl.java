package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.CommentDao;
import com.sifan.livegoods.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

// 商品评论数据访问实现
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 分页查询商品评论
    @Override
    public List<Comment> findCommentsByPage(Query query) {
        return mongoTemplate.find(query, Comment.class);
    }

    // 查询商品有多少个评论
    @Override
    public long countByQuery(String itemId) {
        // 创建聚合查询条件
        TypedAggregation<Comment> typedAggregation =
                new TypedAggregation<Comment>(
                        Comment.class,  // 对应的具体类型是什么，就是用于定位集合的
                        // 提供聚合条件， group分组， 根据itemId字段分组， 返回结果重命名为count。
                        Aggregation.group("itemId").count().as("count")
                );
        AggregationResults<Map> result = mongoTemplate.aggregate(typedAggregation, Map.class);

        long count = Long.parseLong(result.getUniqueMappedResult().get("count").toString());
        return count;
    }
}
