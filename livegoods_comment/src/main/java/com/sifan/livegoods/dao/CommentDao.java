package com.sifan.livegoods.dao;

import com.sifan.livegoods.pojo.Comment;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

// 商品评论数据访问接口
public interface CommentDao {
    // 分页查询评论数据
    List<Comment> findCommentsByPage(Query query);
    // 查询评论数量
    long countByQuery(String itemId);
}
