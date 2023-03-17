package com.sifan.livegoods.service.impl;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.BannerDao;
import com.sifan.livegoods.pojo.Banner;
import com.sifan.livegoods.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Banner轮播图查询服务服务实现
 *
 * @author 思凡
 * @date 2023/03/17
 */
@Repository
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    /**
     * 通过Dao数据访问对象，访问MongoDB，查询轮播图
     * 查询规则： 根据时间降序查询前六条
     *
     * @return {@link LivegoodsResult}
     */
    @Override
    public LivegoodsResult getBanners() {
        // 定义查询条件
        Query query = new Query();
        query.with(
                PageRequest.of(0, 4,
                        Sort.by(Sort.Direction.DESC, "createTime"))
        );
        List<Banner> banners = bannerDao.selectBanners(query);
        List<String> imgUrls = new ArrayList<>();
        for (Banner banner : banners) {
            System.out.println(banner);
            imgUrls.add(banner.getUrl());
        }
        LivegoodsResult livegoodsResult = new LivegoodsResult();
        livegoodsResult.setStatus(200);
        livegoodsResult.setResults(imgUrls);
        return livegoodsResult;
    }
}
