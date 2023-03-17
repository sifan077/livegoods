package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.BannerDao;
import com.sifan.livegoods.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Banner数据访问对象
 * 连接MongoDB 访问Banner集合。 实现数据查询
 *
 * @author 思凡
 * @date 2023/03/17
 */
@Repository
public class BannerDaoImpl implements BannerDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Banner> selectBanners(Query query) {
        return mongoTemplate.find(query, Banner.class);
    }
}
