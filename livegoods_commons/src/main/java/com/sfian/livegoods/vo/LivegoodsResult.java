package com.sfian.livegoods.vo;

import lombok.*;

/**
 * livegoods结果
 * 项目返回结果类型
 * 根据进度逐渐添加需要的属性
 *
 * @author 思凡
 * @date 2023/03/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LivegoodsResult {
    // 响应状态编码
    private int status;
    // 返回结果数据
    private Object results;
    // 提示消息
    private String msg;
}
