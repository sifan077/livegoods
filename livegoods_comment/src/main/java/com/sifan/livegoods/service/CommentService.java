package com.sifan.livegoods.service;

import com.sfian.livegoods.vo.LivegoodsResult;

// 商品评论服务接口
public interface CommentService {
    // 根据商品主键，查询商品评论
    LivegoodsResult findCommentByItemId(String itemId, int page, int rows);
    // 新增商品评论
    LivegoodsResult fellback(String orderId, String comment);

}
