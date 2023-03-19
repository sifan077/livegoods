package com.sifan.livegoods.dao.impl;

import com.sifan.livegoods.dao.ValidateCodeDao;
import com.sifan.livegoods.pojo.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

// 用户登录验证码数据访问实现
@Repository
public class ValidateCodeDaoImpl implements ValidateCodeDao {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 保存数据到redis， 有效期是2分钟
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(2L));
        // redisTemplate.expire(key, 2, TimeUnit.MINUTES);

    }

    // 根据key查询value
    @Override
    public ValidateCode get(String key) {
        return (ValidateCode) redisTemplate.opsForValue().get(key);
    }

    // 根据key删除
    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

}
