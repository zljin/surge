package com.zou.corejava.ooad.singleton;

/**
 * 单例模式：
 * 创建一个独一无二的对象
 * 1.构造器私有
 * 2.在该类中创建
 * 3.给外部提供一个接口访问
 */
public class SingletonEHan {
    private SingletonEHan(){}

    private static SingletonEHan singletonEHan = new SingletonEHan();

    public SingletonEHan getInstance(){
        return singletonEHan;
    }
}
