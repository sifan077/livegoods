package com.sifan.livegoods.service;

import com.sifan.livegoods.pojo.Item;

// 商品详情服务接口
public interface DetailsService {
    // 主键查询商品
    Item getDetails(String id);
}
