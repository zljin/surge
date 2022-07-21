package com.zou.corejava.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author leonard
 * @Description 线程池用法
 * <p>
 * 博客推荐：https://blog.csdn.net/qq_35971258/article/details/115268948
 * <p>
 * 项目中会定义全局共用线程池,启动之创建一次,一直working,不能close
 * 一次性的建议关闭
 *
 * 举例：
 * 一个海底捞就是一个线程池
 * 一共有50个座位  代表核心先线程数
 * 外面等候区20个座位 最大线程数为50+20
 * 排队快到你了，但是你没在，就会有超时时间 代表超时过期
 * 人太多了后面要排3个小时以上,这时决定是等还是换一家 拒绝策略
 *
 * @date 2021-07-29 17:02
 */
public class ThreadPoolManager {

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;
    private static final int CORE_POOL_SIZE = 3;//核心线程数
    private static final int MAX_POOL_SIZE = 10;//最大核心线程数
    private static final int QUEUE_CAPACITY = 50;//阻塞队列长度 即侯客区
    private static final int KEEP_ALIVE_TIME = 60;

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE
                , MAX_POOL_SIZE
                , KEEP_ALIVE_TIME
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(QUEUE_CAPACITY)
                /**
                 *
                 * 四大拒绝策略: 当最大线程数已满,阻塞队列已满
                 * AbortPolicy -> 直接抛出异常
                 * CallerRunsPolicy -> 将任务返回给原来的进程执行
                 * DiscardOldestPolicy -> 此策略将丢弃最早的未处理的任务请求
                 * DiscardPolicy --> 直接抛弃任务
                 */
                , new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
