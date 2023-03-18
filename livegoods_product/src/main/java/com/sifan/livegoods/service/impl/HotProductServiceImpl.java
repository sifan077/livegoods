package com.sifan.livegoods.service.impl;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import com.sifan.livegoods.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 热销商品服务实现
 */
@Service
public class HotProductServiceImpl implements HotProductService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public LivegoodsResult getHotProducts(String city) {

        // 查询热销商品
        Query query = new Query();
        // 查询条件
        query.addCriteria(Criteria.where("city").is(city));
        // 排序和分页
        query.with(
                PageRequest.of(0, 4,
                        Sort.by(Sort.Direction.DESC, "sales"))
        );
        List<Item> items = itemDao.getHotProduct(query);

        if (items.size() < 4) {
            // 查询的热销商品数量不足，需要查询其他城市的热销商品，填充到当前查询结果
            Query otherQuery = new Query();
            // 查询条件， 查询当前城市以外的其他城市热销商品，避免重复数据。
            otherQuery.addCriteria(Criteria.where("city").ne(city));
            // 排序和分页
            otherQuery.with(
                    PageRequest.of(0, 4 - items.size(),
                            Sort.by(Sort.Direction.DESC, "sales"))
            );
            List<Item> otherItems = itemDao.getHotProduct(otherQuery);
            // 将其他城市的热销商品数据，填充到当前城市的热销商品数据集合中。补足4条数据。
            items.addAll(otherItems);
        }

        // 查询结果items，理论上一定有4条数据。如果不足？可以使用托底数据填充。
        if (items.size() < 4) { // 如果所有的热销商品数据总计不足4条，使用托底数据填充。
            for (int i = items.size(); i <= 4; i++) {
                items.add(fallbackItem());
            }
        }
        return LivegoodsResult.ok(items);
    }


    private Item fallbackItem() {
        Item item = new Item();
        item.setId("5ec1ec6b7f56a946fb7fdffa");
        item.setCity("北京");
        item.setHouseType("150 ㎡");
        item.setImgs(
                Arrays.asList(
                        "http://rro1it3zv.hd-bkt.clouddn.com/livehouse/1.png",
                        "http://rro1it3zv.hd-bkt.clouddn.com/livehouse/3.png",
                        "http://rro1it3zv.hd-bkt.clouddn.com/livehouse/4.png"
                )
        );
        item.setPrice(12000L);
        item.setRecommendation(true);
        item.setRecoSort((byte) 9);
        item.setRentType("整租");
        item.setSales(100L);
        item.setTitle("北京高档公寓");
        Map<String, String> info = new HashMap<>();
        info.put("years", "2010");
        info.put("type", "3室2厅");
        info.put("level", "10/18层");
        info.put("style", "精装修");
        info.put("orientation", "南北通透");
        item.setInfo(info);
        return item;
    }
}
