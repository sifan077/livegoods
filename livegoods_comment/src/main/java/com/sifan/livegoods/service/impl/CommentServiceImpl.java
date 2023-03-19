package com.sifan.livegoods.service.impl;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.CommentDao;
import com.sifan.livegoods.dao.OrderDao;
import com.sifan.livegoods.pojo.Comment;
import com.sifan.livegoods.pojo.Order;
import com.sifan.livegoods.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

// 商品评论服务实现
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 分页查询商品评论信息，查询条件是商品的主键。
     * @param itemId 商品主键
     * @param page 查询第几页
     * @param rows 每页多少行
     * @return
     */
    @Override
    public LivegoodsResult findCommentByItemId(String itemId, int page, int rows) {
        // 创建查询条件
        Query query = new Query();
        query.addCriteria(
                Criteria.where("itemId").is(itemId)
        );
        query.with( // 分页逻辑
                PageRequest.of(page, rows)
        );
        // 分页查询商品评论
        List<Comment> commentList = this.commentDao.findCommentsByPage(query);
        long count = this.commentDao.countByQuery(itemId);

        // 隐藏用户手机号。 135 1234 4321 -> 135 **** 4321
        for(Comment comment : commentList){
            String username = comment.getUsername()
                                .replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            comment.setUsername(username);
        }

        LivegoodsResult result = LivegoodsResult.ok(commentList);
        // 总计页码数
        long totalPages = ((count % rows == 0) ? (count / rows) : ((count / rows) + 1));
        if((page + 1) < totalPages) { // 有更多数据
            result.setHasMore(true);
        }else{ // 没有更多数据
            result.setHasMore(false);
        }
        return result;
    }

    /**
     * 新增商品评论
     * @param orderId 订单主键
     * @param comment 评论内容
     * @return
     */
    @Override
    public LivegoodsResult fellback(String orderId, String comment) {
        try {
            // 根据订单主键，查询订单数据
            Order order = orderDao.findById(orderId);

            // 根据查询的订单数据内容，创建一个评论数据对象Comment
            Comment commentObj = new Comment();
            commentObj.setUsername(order.getUsername());
            commentObj.setComment(comment);
            commentObj.setItemId(order.getItemId());
            commentObj.setStar(3); // 前端应用未提供星级请求参数。 赋予默认值 3。

            // 保存数据到MongoDB
            commentDao.save(commentObj);
            // 修改订单状态， commentState = 2， 设置为已评论
            orderDao.updateCommentState(orderId, 2);

            return LivegoodsResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return LivegoodsResult.error();
        }
    }

}
