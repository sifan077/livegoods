package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.LoginLogDao;
import com.sifan.livegoods.pojo.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginMongoDaoImpl implements LoginLogDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(LoginLog loginLog) {
        mongoTemplate.save(loginLog);
    }
}
