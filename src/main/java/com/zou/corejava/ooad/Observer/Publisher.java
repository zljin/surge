package com.zou.corejava.ooad.Observer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Aware:让Bean获得Spring某一个容器的某一个服务
 * 若实现了ApplicationContextAware
 * 调用setApplicationContext方法可以获得applicationContext的上下文
 */
public class Publisher implements ApplicationContextAware {

    private ApplicationContext ctx;
    private String name;

    public Publisher(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void publishArticles(String articleName) {//目标类发布信息
        System.out.println(String.format("<%s>发布,文章<%s>", this.name, articleName));
        ctx.publishEvent(new Event(this.name, this.name, articleName));
    }
}
