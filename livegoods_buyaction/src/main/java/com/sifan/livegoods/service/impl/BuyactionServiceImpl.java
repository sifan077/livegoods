package com.sifan.livegoods.service.impl;

import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import com.sifan.livegoods.service.BuyactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BuyactionServiceImpl implements BuyactionService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public Map<String, Object> buytime(String id) {
        Item item = itemDao.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        // getTime获取时间戳
        result.put("time", item.getBuytime().getTime());
        return result;
    }
}
