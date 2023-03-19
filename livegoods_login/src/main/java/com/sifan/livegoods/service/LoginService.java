package com.sifan.livegoods.service;

import com.sfian.livegoods.vo.LivegoodsResult;

// 用户登录相关服务接口
public interface LoginService {
    LivegoodsResult sendyzm(String phone);
    // 用户登录
    LivegoodsResult login(String username, String password);

}
