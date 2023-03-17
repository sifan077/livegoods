package com.sifan.livegoods.service;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 轮播图服务接口
 *
 * @author 思凡
 * @date 2023/03/17
 */
public interface BannerService {

    /**
     * 轮播图查询
     *
     * @return {@link LivegoodsResult}
     */
    LivegoodsResult getBanners();
}
