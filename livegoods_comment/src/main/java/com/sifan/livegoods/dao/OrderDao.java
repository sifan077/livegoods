package com.sifan.livegoods.dao;

import com.sifan.livegoods.pojo.Order;

// 订单数据访问接口
public interface OrderDao {
    Order findById(String orderId);

    void updateCommentState(String orderId, int commentState);
}
