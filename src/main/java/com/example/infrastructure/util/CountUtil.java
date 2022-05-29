package com.example.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

/**
 * Redis计数器
 */
@Slf4j
@Component
public class CountUtil {

    private static final String PREFIX = "spider:url:count";
    private static RedisTemplate<String, Object> redisTemplate;
    private static RedisAtomicLong entityIdCounter;


    @Autowired
    public CountUtil(@Qualifier("redisCommonTemplate") RedisTemplate<String, Object> redisTemplate) {
        CountUtil.redisTemplate = redisTemplate;
        entityIdCounter = new RedisAtomicLong(PREFIX,redisTemplate.getConnectionFactory());
    }

    public static long get() {
        return entityIdCounter.get();
    }

    public static long cal() {
        return entityIdCounter.incrementAndGet();
    }

    public static long clearZero() {
        entityIdCounter.set(0);
        return entityIdCounter.get();
    }
}
