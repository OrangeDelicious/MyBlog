package com.tianxu.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    @Bean
    public JedisPool getJedisPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(50);//假设操作为0.001s，就可以容纳50000的每秒查询
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxWaitMillis(1000);
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        return jedisPool;
    }
}
