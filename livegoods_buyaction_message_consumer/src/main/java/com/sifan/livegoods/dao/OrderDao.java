package com.sifan.livegoods.dao;

import com.sifan.livegoods.pojo.Order;

// 订单数据访问接口
public interface OrderDao {
    void save(Order order);
}
