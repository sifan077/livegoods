package com.sifan.livegoods.service;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.ItemDao4ES;
import com.sifan.livegoods.pojo.Item4ES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

// 搜索服务实现
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ItemDao4ES itemDao4ES;

    /**
     * 搜索商品逻辑
     *
     * @param city    城市
     * @param content 搜索关键字
     * @param page    第几页， 从0开始
     * @param rows    每页查询多少行
     * @return
     */
    @Override
    public LivegoodsResult search(String city, String content, int page, int rows) {
        // 搜索商品数据
        Page<Item4ES> resultPage =
                this.itemDao4ES.queryForPage(city, content, page, rows);

        // 构建返回结果对象。
        LivegoodsResult result = LivegoodsResult.ok(resultPage.getContent());
        if (page < (resultPage.getTotalPages() - 1)) { // 查询的当前页码小于总计页码
            result.setHasMore(true);
        } else {
            result.setHasMore(false);
        }
        return result;
    }
}
