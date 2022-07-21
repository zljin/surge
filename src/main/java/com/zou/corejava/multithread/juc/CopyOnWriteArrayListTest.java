package com.zou.corejava.multithread.juc;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 操作过程：
 * 先拷贝一份数组,在拷贝的数组中写数据,写完后丢弃原来的数组,指向拷贝后的数组
 * <p>
 * 适用于读多写少的场景,因为写多会导致拷贝数组的量过多,最后导致栈溢出
 *
 * 此集合只能保证最终一致性,因为整个是需要时间过程的,所以不能保证实时性
 */
public class CopyOnWriteArrayListTest {

    // 创建一个 CountDownLatch 对象，代表黑名单列表
    private static CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    // 模拟初始化的黑名单数据
    static {
        copyOnWriteArrayList.add("ipAddr0");
        copyOnWriteArrayList.add("ipAddr1");
        copyOnWriteArrayList.add("ipAddr2");
    }

    // 主线程
    public static void main(String[] args) throws InterruptedException {

        //读
        Runnable task = new Runnable() {
            @Override
            public void run() {
                // 模拟接入用时
                try {
                    Thread.sleep(new Random().nextInt(5000));
                } catch (Exception e) {
                }

                String currentIP = "ipAddr" + new Random().nextInt(5);
                if (copyOnWriteArrayList.contains(currentIP)) {
                    System.out.println(Thread.currentThread().getName() + " IP " + currentIP + "命中黑名单，拒绝接入处理");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " IP " + currentIP + "接入处理...");
            }
        };
        new Thread(task, "请求1").start();
        new Thread(task, "请求2").start();
        new Thread(task, "请求3").start();

        /**
         * 写的时候拷贝集合，在拷贝的集合中写内容，之后将指针指向新拷贝的集合
         */
        Runnable updateTask = new Runnable() {
            public void run() {
                // 模拟用时
                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (Exception e) {
                }

                String newBlackIP = "ipAddr3";
                copyOnWriteArrayList.add(newBlackIP);
                System.out.println(Thread.currentThread().getName() + " 添加了新的非法IP " + newBlackIP);
            }
        };
        new Thread(updateTask, "IP黑名单更新").start();

        Thread.sleep(1000000);
    }
}
