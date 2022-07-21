package com.zou.corejava.ooad.Observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * ApplicationListener 监听器方法，监听到event发起对象的响应
 */
public class Listener implements ApplicationListener {

    private String name;

    public Listener(String name) {
        this.name = name;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof Event){
            Event event = (Event) applicationEvent;
            System.out.println(String.format("粉丝<%s>,收到<%s>的文章<%s>",
                    name,event.publisher,event.articleName));
        }
    }
}
