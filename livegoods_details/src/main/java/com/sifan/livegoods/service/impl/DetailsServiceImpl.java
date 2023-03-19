package com.sifan.livegoods.service.impl;

import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import com.sifan.livegoods.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

// 商品详情服务实现
@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private ItemDao itemDao;

    /**
     * 主键查询商品。
     * 需要将商品中的图片地址，从相对路径修改为绝对路径。
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = "com:livegoods:details", key = "'getDetails('+#id+')'")
    public Item getDetails(String id) {
        // 主键查询
        return itemDao.findItemById(id);
    }
}
