package com.sifan.livegoods.pojo;

import lombok.*;

import java.util.Date;

/**
 * 轮播图实体
 *
 * @author 思凡
 * @date 2023/03/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Banner {
    // 主键
    private String id;
    // 地址
    private String url;
    // 创建时间
    private Date createTime;
}
