package com.zou.corejava.multithread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition await 和 signal 方法
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ProductFactory productFactory = new ProductFactory();
        new Thread(new Producer(productFactory), "1号生产者").start();
        new Thread(new Producer(productFactory), "2号生产者").start();
        new Thread(new Consumer(productFactory), "1号消费者").start();
        new Thread(new Consumer(productFactory), "2号消费者").start();
        new Thread(new Consumer(productFactory), "3号消费者").start();
    }
}


class ProductFactory {
    private LinkedList<String> products; //根据需求定义库存，用 LinkedList 实现
    private int capacity = 10; // 根据需求：定义最大库存 10
    private Lock lock = new ReentrantLock(false);
    private Condition p = lock.newCondition();
    private Condition c = lock.newCondition();

    public ProductFactory() {
        products = new LinkedList<String>();
    }

    // 根据需求：produce 方法创建
    public void produce(String product) {
        try {
            lock.lock();
            while (capacity == products.size()) { //根据需求：如果达到 10 库存，停止生产
                try {
                    System.out.println("警告：线程(" + Thread.currentThread().getName() + ")准备生产产品，但产品池已满");
                    p.await(); // 库存达到 10 ，生产线程进入 wait 状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            products.add(product); //如果没有到 10 库存，进行产品添加
            System.out.println("线程(" + Thread.currentThread().getName() + ")生产了一件产品:" + product + ";当前剩余商品" + products.size() + "个");
            c.signalAll(); //生产了产品，通知消费者线程从 wait 状态唤醒，进行消费
        } finally {
            lock.unlock();
        }
    }

    // 根据需求：consume 方法创建
    public String consume() {
        try {
            lock.lock();
            while (products.size() == 0) { //根据需求：没有库存消费者进入wait状态
                try {
                    System.out.println("警告：线程(" + Thread.currentThread().getName() + ")准备消费产品，但当前没有产品");
                    c.await(); //库存为 0 ，无法消费，进入 wait ，等待生产者线程唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String product = products.remove(0); //如果有库存则消费，并移除消费掉的产品
            System.out.println("线程(" + Thread.currentThread().getName() + ")消费了一件产品:" + product + ";当前剩余商品" + products.size() + "个");
            p.signalAll();// 通知生产者继续生产
            return product;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private ProductFactory productFactory; //关联工厂类，调用 produce 方法

    public Producer(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    @Override
    public void run() {
        int i = 0; // 根据需求，对产品进行编号
        while (true) {
            productFactory.produce(String.valueOf(i)); //根据需求 ，调用 productFactory 的 produce 方法
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}

class Consumer implements Runnable {
    private ProductFactory productFactory;

    public Consumer(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    @Override
    public void run() {
        while (true) {
            productFactory.consume();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}