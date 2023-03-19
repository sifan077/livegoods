import com.sifan.livegoods.SearchApp;
import com.sifan.livegoods.dao.ItemDao4ES;
import com.sifan.livegoods.pojo.Item;
import com.sifan.livegoods.pojo.Item4ES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {SearchApp.class})
@RunWith(SpringRunner.class)
public class TestSearch {

    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private ItemDao4ES itemDao;

    @Test
    public void testInitES() {
        // 从MongoDB中查询所有的商品数据
        List<Item> items = mongoTemplate.findAll(Item.class);
        // 转换Item类型 -> Item4ES类型
        List<Item4ES> item4ESList = new ArrayList<>();
        for (Item item : items) {
            Item4ES i = new Item4ES();
            i.setId(item.getId());
            i.setTitle(item.getTitle());
            i.setCity(item.getCity());
            i.setHouseType(item.getHouseType4Search());
            i.setImg(item.getImg());
            i.setPrice("<h3>" + item.getPrice() + "</h3>");
            i.setRentType(item.getRentType());
            item4ESList.add(i);
        }
        // 将Item4ES类型的对象保存到ElasticSearch中。
        itemDao.batchIndex(item4ESList);
    }

    @Test
    public void getItems(){
        Page<Item4ES> item4ESPage = itemDao.queryForPage("北京", "老", 0, 4);
        System.out.println(item4ESPage.getTotalElements());
        System.out.println(item4ESPage.getTotalPages());
        for (Object o : item4ESPage.stream().toArray()) {
            System.out.println(o);
        }
    }
}
