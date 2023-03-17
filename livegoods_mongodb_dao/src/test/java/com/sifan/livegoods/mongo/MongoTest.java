package com.sifan.livegoods.mongo;


import com.sifan.livegoods.mongodb.MongoDBApp;
import com.sifan.livegoods.pojo.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@SpringBootTest(classes = MongoDBApp.class)
//@RunWith(SpringRunner.class)
public class MongoTest {

//    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
    public void sayHello() {
        List<Banner> list = new ArrayList<>();
        Banner banner1 = new Banner();
        banner1.setCreateTime(new Date());
        banner1.setUrl("http://rro1it3zv.hd-bkt.clouddn.com/livehouse/1.png");
        Banner banner2 = new Banner();
        banner2.setCreateTime(new Date());
        banner2.setUrl("http://rro1it3zv.hd-bkt.clouddn.com/livehouse/2.png");
        Banner banner3 = new Banner();
        banner3.setCreateTime(new Date());
        banner3.setUrl("http://rro1it3zv.hd-bkt.clouddn.com/livehouse/3.png");
        Banner banner4 = new Banner();
        banner4.setCreateTime(new Date());
        banner4.setUrl("http://rro1it3zv.hd-bkt.clouddn.com/livehouse/4.png");
        Banner banner5 = new Banner();
        banner5.setCreateTime(new Date());
        banner5.setUrl("http://rro1it3zv.hd-bkt.clouddn.com/livehouse/5.png");
        Banner banner6 = new Banner();
        banner6.setCreateTime(new Date());
        banner6.setUrl("http://rro1it3zv.hd-bkt.clouddn.com/livehouse/6.png");
        list.add(banner1);
        list.add(banner2);
        list.add(banner3);
        list.add(banner4);
        list.add(banner5);
        list.add(banner6);
        mongoTemplate.insert(list, Banner.class);
        System.out.println(list);
    }
}
