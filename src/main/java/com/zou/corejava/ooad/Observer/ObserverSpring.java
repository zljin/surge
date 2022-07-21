package com.zou.corejava.ooad.Observer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 观察者模式：
 * 叫做订阅者模式更好理解
 * 一个目标对象状态发生变化,通知所有订阅者并进行相应的响应
 *
 * 下面基于Spring实现观察者模式,分三步
 * 1. 定义目标对象
 * 2. 定义监听对象
 * 3. 定义通知对象,即事件的本身
 */
public class ObserverSpring {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(RegisterBean.class);
        Publisher publisher = (Publisher) ac.getBean("publisheryyds");
        publisher.setName("肖战");
        publisher.setApplicationContext(ac);
        publisher.publishArticles("新闻123");
    }
}


