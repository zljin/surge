package com.zou.corejava.ooad.Observer;

import org.springframework.context.ApplicationEvent;

/**
 * ApplicationEvent:
 *  spring的事件本身,自定义该类需要继承该类,可用来传递数据
 */
public class Event extends ApplicationEvent {
    public String publisher;
    public String articleName;


    public Event(Object source,String publisher,String articleName) {
        super(source);
        this.publisher = publisher;
        this.articleName = articleName;
    }
}
