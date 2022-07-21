package com.zou.corejava.ooad.singleton;


/**
 * 枚举类实现实例化
 */
public enum SingletonEnum {

    //等效于:  public static final SingletonEnum instance = new SingletonEnum();
    instance;

    private SingletonEnum(){}

    public void method(){}
}
