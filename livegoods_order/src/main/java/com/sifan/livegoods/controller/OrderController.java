package com.sifan.livegoods.controller;

import com.sifan.livegoods.pojo.Order;
import com.sifan.livegoods.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public List<Order> getOrders(String user) {
        System.out.println(user);
        return orderService.getOrders(user);
    }

}
