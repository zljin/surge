package com.example.infrastructure.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leonard
 * @date 2022/6/8
 * @Description 计数器工具类
 */
public class AtomicCountUtil {

    private static final AtomicInteger cal = new AtomicInteger(0);

    private AtomicCountUtil() {
    }

    public static void cal(){
        cal.getAndAdd(1);
    }

    public static Integer get(){
        return cal.get();
    }

    public static void clearZero(){
        cal.set(0);
    }
}
