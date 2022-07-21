package com.zou.corejava.multithread.juc;


import com.alibaba.fastjson.JSON;
import com.zoulj.multithread.ThreadPoolManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 研发场景：
 * 统计3个文本中英文字母出现的总次数,为了加快统计效率,采用3个线程每个线程处理一个文本
 *
 */
public class ConcurrentHashMapTest {

    //存放统计结果
    private static Map<String, AtomicLong> concurrentHashMap = new ConcurrentHashMap<>();
    private static Map<String, AtomicLong> concurrentHashMap2 = new ConcurrentHashMap<>();
    private static Map<String, AtomicLong> hashMap = new HashMap<>();
    /**
     * CountDownLatch
     * 倒计数器，每次执行一个多线程，计数器减去1,当减为0的时候,主线程开始执行
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static AtomicLong count = new AtomicLong(0);

    private static String[] fileList = {
            "aabbccfsadfgscbaradgatfgasgwergadgffdge4rtgeasgdsadqwetgbnnkouopugzvxcbdfg",
            "zusasduikwficm,.apapmemgnnzzvpalfiodfasfqtrtsdbhggjhgjthhsrtyrsthtsdhrtsrtvg",
            "ppadhyasiopaidfjaaeoopguivbxcbnfgdutyuigdhnxvawercxbvsdfhjxsfghfdfebhrxsgfsdfegfgh"
    };

    public static void wayOne() {
        System.out.println("hashMap 开始=================================");
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < fileList.length; i++) {
            char[] charArray = fileList[i].toCharArray();
            for (int k = 0; k < charArray.length; k++) {
                AtomicLong value = hashMap.get(charArray[k] + "");
                if (value == null) {
                    value = new AtomicLong(0);
                    hashMap.put(charArray[k] + "", value);
                } else {
                    hashMap.put(charArray[k] + "", new AtomicLong(value.incrementAndGet()));
                }
            }
        }
        System.out.println("hashMap 统计结果为：" + " " + JSON.toJSONString(hashMap));
        System.out.println("hashMap 耗时=====================================：" + (System.currentTimeMillis() - t1) + "ms");
    }

    public static void wayTo() throws Exception {
        System.out.println("concurrentHashMap 开始=================================");
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < fileList.length; i++) {
            /**
             * 阻塞队列
             * 多线程向bq队列添加数据时
             * 1. 队列若满了,则继续添加,进程阻塞
             * 2. 若取出数据,队列为空,继续取出,进程阻塞
             */
            BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(1);
            queue.put(i);
            ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(() -> {
                try {
                    Integer take = queue.take();
                    char[] charArray = fileList[take].toCharArray();
                    for (int k = 0; k < charArray.length; k++) {
                        AtomicLong value = concurrentHashMap.get(charArray[k] + "");
                        if (value == null) {
                            value = new AtomicLong(0);
                            concurrentHashMap.put(charArray[k] + "", value);
                        } else {
                            concurrentHashMap.put(charArray[k] + "", new AtomicLong(value.incrementAndGet()));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("error:" + e.getStackTrace());
                } finally {
                    System.out.println("执行" + count.addAndGet(1) + "次");
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("执行完" + count.get() + "次。结束");
        ThreadPoolManager.THREAD_POOL_EXECUTOR.shutdown();
        System.out.println("concurrentHashMap 统计结果为：" + " " + JSON.toJSONString(concurrentHashMap));
        System.out.println("concurrentHashMap 耗时=====================================：" + (System.currentTimeMillis() - t1) + "ms");
    }

    /**
     * CompletableFuture改写wayTo
     * jdk8的 CompletableFuture异步请求
     * 该异步变成用于非阻塞进程,运行任务与主线程隔离,并通知主线程执行成功还是失败
     * 若执行future.get,则变为同步方法
     */
    public static void way3() throws Exception {
        System.out.println("concurrentHashMap2 开始=================================");
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < fileList.length; i++) {
            BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
            queue.put(i);
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Integer take = queue.take();
                    char[] charArray = fileList[take].toCharArray();
                    for (int k = 0; k < charArray.length; k++) {
                        AtomicLong value = concurrentHashMap2.get(charArray[k] + "");
                        if (value == null) {
                            value = new AtomicLong(0);
                            concurrentHashMap2.put(charArray[k] + "", value);
                        } else {
                            concurrentHashMap2.put(charArray[k] + "", new AtomicLong(value.incrementAndGet()));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("error:" + e.getStackTrace());
                } finally {
                    System.out.println("执行" + count.addAndGet(1) + "次");
                    countDownLatch.countDown();
                }
                return "end.";
            }, ThreadPoolManager.THREAD_POOL_EXECUTOR);
        }
        countDownLatch.await();
        System.out.println("执行完" + count.get() + "次。结束");
        ThreadPoolManager.THREAD_POOL_EXECUTOR.shutdown();
        System.out.println("concurrentHashMap2 统计结果为：" + " " + JSON.toJSONString(concurrentHashMap));
        System.out.println("concurrentHashMap2 耗时=====================================：" + (System.currentTimeMillis() - t1) + "ms");
    }


    public static void main(String[] args) throws Exception {
        wayOne();
        wayTo();
        way3();
    }
}
