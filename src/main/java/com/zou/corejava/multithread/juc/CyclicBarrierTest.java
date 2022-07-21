package com.zou.corejava.multithread.juc;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环屏障,允许一组线程相互等待,直到所有线程到达一个公共的屏障点
 * ,这些进程再一起执行后续的逻辑,此屏障和重复使用
 * 适合多个线程协作完成任务的场合
 *
 * 例子：班车只有等人都到齐了才能开
 */
public class CyclicBarrierTest {
    // 创建一个 Runnable 对象，用于屏障解除时处理全局逻辑，在此例子中代表大巴司机
    private static Runnable driver = new Runnable() {
        public void run() {
            System.out.println("所有同学已经集合完毕，开始启动车辆出发。");
        }
    };

    // 创建一个 CyclicBarrier 对象，初始化为 5, 代表需要控制同步的线程个数，在此例子中代表 5 位同学
    static int threadCount = 5;

    //CyclicBarrier (int parties, Runnable barrierAction)屏障点到达后的其他动作
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, driver);

    public static void main(String[] args) throws InterruptedException {
        // 模拟同学
        for(int i=1; i<=threadCount; i++) {
            // 模拟某个同学的动作
            new Thread(new Runnable() {
                @SneakyThrows
                public void run() {
                    System.out.println( Thread.currentThread().getName() + "已经开始出门...");
                    // 模拟同学出门赶往集合点的用时
                    try {
                        Thread.sleep(new Random().nextInt(10000));
                    } catch (Exception e) {}
                    System.out.println( Thread.currentThread().getName() + "已经到达集合点");
                    // 等待其他同学到达集合点（等待其他线程到达屏障点）
                    cyclicBarrier.await();
                }
            }, i + "号同学").start();
        }
    }
}

