package com.zou.corejava.multithread.leetcode;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * print-foobar-alternately
 * https://leetcode-cn.com/problems/print-foobar-alternately/ 多线程六脉神剑
 */
public class FooBarTest {
    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(10);
        new Thread(new printFoo(fooBar)).start();
        new Thread(new printBar(fooBar)).start();
    }

    static class FooBar {
        private int n;
        private BlockingDeque<Integer> bar = new LinkedBlockingDeque<>(1);
        private BlockingDeque<Integer> foo = new LinkedBlockingDeque<>(1);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                foo.put(i);
                printFoo.run();
                bar.put(i);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                bar.take();
                printBar.run();
                foo.take();
            }
        }
    }

    /*class FooBar {
    private int n;
    //permits:1 第一个调用acquire就会成功,其余将阻塞除非释放,直到第一个释放
    private Semaphore foo = new Semaphore(1);
    //permits:0 只要调用就阻塞,直到你release
    private Semaphore bar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }
}*/

/*class FooBar {
    private int n;

    private Lock lock = new ReentrantLock();
    private Condition con = lock.newCondition();

    private volatile boolean flag = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while(!flag){
                con.await();
            }
            printFoo.run();
            flag = false;
            con.signal();
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while(flag){
                con.await();
            }
            printBar.run();
            flag = true;
            con.signal();
            lock.unlock();
        }
    }
}*/

/*//自旋让出CPU
class FooBar {
    private int n;
    private volatile boolean flag = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n;) {
            if(flag){
                printFoo.run();
                i++;
                flag = false;
            }else {
                Thread.yield();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n;) {
            if(!flag){
                printBar.run();
                i++;
                flag = true;
            }else {
                Thread.yield();
            }
        }
    }
}*/


/*class FooBar {
    private int n;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    volatile boolean fin = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while(!fin);
            printFoo.run();
            fin = false;
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            printBar.run();
            fin = true;
        }
    }
}*/


    static class printBar implements Runnable {

        FooBar fooBar;

        public printBar(FooBar fooBar) {
            this.fooBar = fooBar;
        }

        @Override
        public void run() {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class printFoo implements Runnable {

        FooBar fooBar;

        public printFoo(FooBar fooBar) {
            this.fooBar = fooBar;
        }

        @Override
        public void run() {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}









