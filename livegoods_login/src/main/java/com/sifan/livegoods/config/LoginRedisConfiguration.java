package com.sifan.livegoods.config;

import com.sifan.livegoods.cache.config.RedisCacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 登录系统中的配置类型， 配置Redis相关内容
 */
@Configuration
public class LoginRedisConfiguration extends RedisCacheConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        return super.redisTemplate(redisConnectionFactory);
    }
}
