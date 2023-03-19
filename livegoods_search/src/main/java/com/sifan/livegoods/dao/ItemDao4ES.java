package com.sifan.livegoods.dao;

import com.sifan.livegoods.pojo.Item4ES;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemDao4ES {
    // 批量数据新增到ES
    void batchIndex(List<Item4ES> items);

    // 单数据新增到ES
    void index(Item4ES item);

    // 分页查询

    Page<Item4ES>queryForPage(String city, String content, int page, int rows);
}
