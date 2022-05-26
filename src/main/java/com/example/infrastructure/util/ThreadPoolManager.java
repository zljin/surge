package com.example.infrastructure.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author leonard
 * @Description 线程池用法
 * @date 2021-07-29 17:02
 */
public class ThreadPoolManager {

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 4;
    private static final int QUEUE_CAPACITY = 10;
    private static final int KEEP_ALIVE_TIME = 30;

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE
                , MAX_POOL_SIZE
                , KEEP_ALIVE_TIME
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(QUEUE_CAPACITY)
                /**
                 *
                 * 四大拒绝策略: 当最大线程树已满,阻塞队列已满
                 * AbortPolicy -> 直接抛出异常
                 * CallerRunsPolicy -> 将任务返回给原来的进程执行
                 * DiscardOldestPolicy ->
                 * DiscardPolicy --> 直接抛弃任务
                 */
                , new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
