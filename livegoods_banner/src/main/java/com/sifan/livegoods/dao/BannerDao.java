package com.sifan.livegoods.dao;

import com.sifan.livegoods.pojo.Banner;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

/**
 * 轮播图数据访问对象，实现访问查询
 *
 * @author 思凡
 * @date 2023/03/17
 */
public interface BannerDao {
    List<Banner> selectBanners(Query query);
}
