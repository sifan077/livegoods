package com.sfian.livegoods.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * livegoods结果
 * 项目返回结果类型
 * 根据进度逐渐添加需要的属性
 *
 * @author 思凡
 * @date 2023/03/17
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LivegoodsResult {
    // 响应状态编码。 200 - 成功；  500 - 失败；
    private int status;
    // 返回结果数据； Banner轮播图使用，用于保存图片地址集合。
    private Object results;
    // 提示消息； Banner轮播图使用， 用于查询错误时返回提示消息。
    private String msg;
    // 返回的数据； 热销商品使用，返回查询的热销商品集合；热门推荐使用；
    private Object data;

    // 是否还有更多数据
    private Boolean hasMore;

    public static LivegoodsResult ok() {
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(200);
        return result;
    }

    public static LivegoodsResult ok(Object data) {
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    public static LivegoodsResult error() {
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(500);
        return result;
    }

    public static LivegoodsResult error(String msg) {
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
