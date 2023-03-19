package com.sifan.livegoods.controller;

import com.sifan.livegoods.service.BuyactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class BuyactionController {
    @Autowired
    private BuyactionService buyactionService;

    @GetMapping("/buytime")
    public Map<String, Object> buytime(String id) {
        return buyactionService.buytime(id);
    }
}
