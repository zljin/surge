package com.example.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis计数器
 */
@Slf4j
@Component
public class CountUtil {

    @Resource(name = "redisCommonTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private static final String PREFIX="spider:url:count";

    private RedisAtomicLong entityIdCounter = new RedisAtomicLong(PREFIX, redisTemplate.getConnectionFactory());


    public long get(){
        return entityIdCounter.get();
    }

    public long cal(){
        return entityIdCounter.incrementAndGet();
    }

    public long clearZero(){
        entityIdCounter.set(0);
        return entityIdCounter.get();
    }
}