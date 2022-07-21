package com.zou.corejava.multithread.juc;


import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * 供了两个线程在某个时间点彼此交换信息的功能。
 * 使用 Exchanger 的重点
 * 1. 一对线程使用 同一个Exchanger 对象 和 exchange () 方法，
 * 2. 当一对线程都到达了同步点时，彼此会进行信息交换
 */
public class ExchangerTest {

    // 创建一个 Exchanger 对象
    private static Exchanger<Object> exchanger = new Exchanger();

    public static void main(String[] args) throws InterruptedException {
        // 模拟快递员
        new Thread(() -> {
            System.out.println( Thread.currentThread().getName() + "送货上门中...");
            // 模拟快递送货用时
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (Exception e) {}
            System.out.println( Thread.currentThread().getName() + "货物已经送到，等待客户付款");
            // 进行货款交换
            try {
                Object money = exchanger.exchange("快递件");
                // 收到货款
                System.out.println("已经收到货款" + money + "，继续下一单派送...");
            } catch (Exception e) {}
        }, "快递员").start();

        // 模拟客户
        new Thread(() -> {
            System.out.println( Thread.currentThread().getName() + "工作中...");
            // 模拟工作中用时
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (Exception e) {}
            System.out.println( Thread.currentThread().getName() + "接到快递员取件电话，货物已经送到");
            try {
                // 进行货款交换
                Object packagz = exchanger.exchange("1000元");
                // 收到货款
                System.out.println("已经收到货物" + packagz + "...");
            } catch (Exception e) {}
        }, "客户").start();

    }
}
