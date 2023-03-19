package com.sifan.livegoods.dao;

import com.sifan.livegoods.pojo.Order;

import java.util.List;

// 订单数据访问接口
public interface OrderDao {
    // 根据用户手机号，查询订单。不分页，查询全部。
    List<Order> getOrders(String user);
}
