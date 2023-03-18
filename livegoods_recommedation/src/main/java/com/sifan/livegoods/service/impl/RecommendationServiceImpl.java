package com.sifan.livegoods.service.impl;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.ItemDao;
import com.sifan.livegoods.pojo.Item;
import com.sifan.livegoods.service.RecommendationService;
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
 * 热门推荐服务实现
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired
    private ItemDao itemDao;

    /**
     * 查询条件是： 城市 = 方法参数 and 是否推荐 = true
     * @param city 城市
     * @return
     */
    @Override
    public LivegoodsResult getRecommendation(String city) {
        // 查询条件
        Query query = new Query();
        Criteria c = new Criteria();
        // 查询条件， 城市 = 参数值 and 是否推荐 = true
        c.andOperator(
                Criteria.where("city").is(city),
                Criteria.where("recommendation").is(true)
        );
        query.addCriteria(c);
        // 分页和排序
        query.with(
                PageRequest.of(0, 4,
                        Sort.by(Sort.Direction.DESC, "recoSort"))
        );
        List<Item> items = itemDao.selectRecommendation(query);

        // 判断查询结果数量是否达标。
        if(items.size() < 4){
            // 查询的热门推荐商品数量少于4。 从其他城市的推荐商品中查询
            Query otherQuery = new Query();
            Criteria otherCriteria = new Criteria();
            otherCriteria.andOperator(
                    Criteria.where("city").ne(city),
                    Criteria.where("recommendation").is(true)
            );
            otherQuery.addCriteria(otherCriteria);
            otherQuery.with(
                    PageRequest.of(0, 4 - items.size(),
                            Sort.by(Sort.Direction.DESC, "recoSort"))
            );
            List<Item> otherItems = itemDao.selectRecommendation(otherQuery);
            items.addAll(otherItems);
        }

        if(items.size() < 4){
            // 所有城市的热门推荐数据，总量少于4条。使用托底数据补足。
            for(int i = items.size(); i < 4; i++){
                items.add(fallbackItem());
            }
        }

        // 返回结果
        return LivegoodsResult.ok(items);
    }



    // 创建托底数据
    private Item fallbackItem(){
        Item item = new Item();
        item.setId("5ec1ec6b7f56a946fb7fdffa");
        item.setCity("北京");
        item.setHouseType("150 ㎡");
        item.setImgs(
                Arrays.asList(
                        "http://rro1it3zv.hd-bkt.clouddn.com/livehouse/2.png",
                        "http://rro1it3zv.hd-bkt.clouddn.com/livehouse/5.png",
                        "http://rro1it3zv.hd-bkt.clouddn.com/livehouse/6.png"
                )
        );
        item.setPrice(12000L);
        item.setRecommendation(true);
        item.setRecoSort((byte)9);
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
