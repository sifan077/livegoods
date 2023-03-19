package com.sifan.livegoods.controller;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/sendyzm")
    public LivegoodsResult sendyzm(String phone) {
        return loginService.sendyzm(phone);
    }

    @PostMapping("/login")
    public LivegoodsResult login(String username, String password) {
        return loginService.login(username, password);
    }


}
