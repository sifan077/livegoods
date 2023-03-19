package com.sifan.livegoods.controller;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 商品评论控制器
@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 根据商品主键查询商品评论
     * @param itemId
     * @return
     */
    @GetMapping("/comment")
    public LivegoodsResult getCommentByItemId(
            @RequestParam(value = "id") String itemId, int page,
            @RequestParam(defaultValue = "5") int rows){
        return commentService.findCommentByItemId(itemId, page, rows);
    }
}
