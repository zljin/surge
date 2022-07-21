package com.zou.corejava.multithread.juc;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 经常用于限制同一时刻获取某种资源的线程数量，最为典型的就是做流量控制
 *
 * 比如 WEB 服务器处理能力有限，需要控制网络请求接入的最大连接数
 * 以防止过大的请求流量压垮我们的服务器，导致整个应用不能正常提供服务。
 */
public class SemaphoreTest {
    // 先定义一个Semaphore信号量对象
    private static Semaphore semaphore = new Semaphore(3);

    // 测试方法
    public static void main(String[] args) {

        // 定义10个人过闸机
        for(int i=0; i<10; i++) {
            Person person = new Person(semaphore, i);
            new Thread(person).start();
        }
    }
}

class Person implements Runnable {

    private Semaphore semaphore;
    private String persionName;

    public Person(Semaphore semaphore, int persionNo) {
        this.semaphore = semaphore;
        this.persionName = "旅客" + persionNo;
    }

    public void run() {
        try {
            // 请求获得信号量，就是请求（寻找）是否有可用的闸机
            semaphore.acquire();
            // 已经等到了可用闸机
            System.out.println(this.persionName + "已经占有一台闸机");
            // 进站
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 已经进站
            System.out.println(this.persionName + "已经进站");
            // 让出闸机给别人用
            semaphore.release();
        }
    }
}