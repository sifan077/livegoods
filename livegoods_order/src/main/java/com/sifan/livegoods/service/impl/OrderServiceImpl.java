package com.sifan.livegoods.service.impl;

import com.sifan.livegoods.dao.OrderDao;
import com.sifan.livegoods.pojo.Order;
import com.sifan.livegoods.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 订单服务实现
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrders(String user) {
        return orderDao.getOrders(user);
    }
}
