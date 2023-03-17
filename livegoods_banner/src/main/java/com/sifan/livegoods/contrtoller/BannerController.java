package com.sifan.livegoods.contrtoller;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 处理轮播图查询
     *
     * @return {@link Object}
     */
    @GetMapping("/banner")
    public LivegoodsResult banner() {

        return bannerService.getBanners();
    }
}
