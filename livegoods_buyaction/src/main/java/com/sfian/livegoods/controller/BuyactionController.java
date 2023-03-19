package com.sfian.livegoods.controller;

import com.sfian.livegoods.service.BuyactionService;
import com.sfian.livegoods.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyactionController {
    @Autowired
    private BuyactionService buyactionService;

    /**
     * 预定房屋
     * @param id 商品主键
     * @param user 登录用户手机号
     * @return 预定结果
     */
    @GetMapping("/buyaction")
    public LivegoodsResult buyaction(String id, String user){
        return buyactionService.buyaction(id, user);
    }
}
