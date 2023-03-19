package com.sifan.livegoods.dao;

// 商品数据访问接口
public interface ItemDao {
    // 更新商品数据， 是否已出租字段。
    long update(String id, Boolean rented);
}
