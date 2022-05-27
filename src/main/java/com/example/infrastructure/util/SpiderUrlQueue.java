package com.example.infrastructure.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author leonard
 * @date 2022/5/27
 * @Description 待爬url的阻塞队列
 */
public class SpiderUrlQueue {

    private SpiderUrlQueue() {
    }

    //未爬过的网页url
    private static BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();

    public static BlockingQueue<String> get(){
        return urlQueue;
    }
}
