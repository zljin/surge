package com.zou.corejava.multithread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 业务场景：查询商品详情页逻辑复杂
 * 有些数据需要远程调用，非常耗时，所以我们采用异步的线程方式进行处理
 *
 * com.xunqi.gulimall.order.service.impl.OrderServiceImpl#confirmOrder
 * com.xunqi.gulimall.product.service.impl.SkuInfoServiceImpl#item
 * <p>
 * 通过线程池性能稳定，也可以获取执行结果，并捕获异常。但是，在业务复杂情况下，一
 * 个异步调用可能会依赖于另一个异步调用的执行结果
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        //main1();
        //main2();
        main3();
    }

    public static void main3() throws Exception {
        /**
         * 线程串行化
         * 1、thenRunL：不能获取上一步的执行结果
         * 2、thenAcceptAsync：能接受上一步结果，但是无返回值
         * 3、thenApplyAsync：能接受上一步结果，有返回值
         */
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, ThreadPoolManager.THREAD_POOL_EXECUTOR).thenApplyAsync(res -> {
            System.out.println("任务2启动了..." + res);
            return "Hello" + res;
        }, ThreadPoolManager.THREAD_POOL_EXECUTOR);
        System.out.println("main......end....." + future.get());
    }


    /**
     * handle 和 complete 一样，可对结果做最后的处理（可处理异常），可改变返回值
     * 方法执行完后端处理
     * @throws Exception
     */
    public static void main2() throws Exception {
         CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
             System.out.println("当前线程：" + Thread.currentThread().getId());
             int i = 10 / 0;
             System.out.println("运行结果：" + i);
             return i;
         }, ThreadPoolManager.THREAD_POOL_EXECUTOR).handle((result,thr) -> {
             if (result != null) {
                 return result * 2;
             }
             if (thr != null) {
                 System.out.println("异步任务成功完成了...结果是：" + result + "异常是：" + thr);
                 return 0;
             }
             return 0;
         });
    }


    /**
     * 方法完成后的处理
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t future1");
            int i = 10 / 0;
            return 1024;
        }, ThreadPoolManager.THREAD_POOL_EXECUTOR).whenCompleteAsync((obj, throwable) -> {
            //whenComplete 当前任务线程执行计算后的结果 和whenCompleteAsync区别在于另一个异步执行
            //依赖其他线程执行
            System.out.println("----obj:" + obj.toString());
            System.out.println("----throwable:" + throwable);
        }, ThreadPoolManager.THREAD_POOL_EXECUTOR).exceptionally((throwable) -> {
            //异常返回的结果
            System.out.println("throwable: " + throwable);
            return 666;
        });
        System.out.println(future1.get());
    }


    /**
     * * 初始化线程的4种方式：
     * * 1. extends thread
     * * 2. implement Runnable
     * * 3. implement Callable + FutureTask
     * * 4. 线程池
     */
    @Test
    public void main000() throws Exception {
//        System.out.println("main......start.....");
//        Thread thread = new Thread01();
//        thread.start();
//        System.out.println("main......end.....");
//
//        Runable01 runable01 = new Runable01();
//        new Thread(runable01).start();

        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }


    public static class Runable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }


    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }
    }
}
