package com.sfian.livegoods.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 购买商品的消息类型
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LivegoodsBuyMessage extends LivegoodsMessage {
    // 购买商品的主键
    private String itemId;
    // 购买商品的用户
    private String user;

}
