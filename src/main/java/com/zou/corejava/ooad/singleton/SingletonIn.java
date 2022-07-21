package com.zou.corejava.ooad.singleton;

/**
 * 内部类和饿汉式都采用类装载的机制保证初始化只有一个实例对象
 */
public class SingletonIn {

    private SingletonIn(){}

/*    interface SingletonFactorys{
        SingletonIn instance = new SingletonIn();
    }

    public static SingletonIn getInstance2(){
        return SingletonFactory.instance;
    }*/


    private static class SingletonFactory{
        private static final SingletonIn instance = new SingletonIn();
    }

    public static SingletonIn getInstance(){
        return SingletonFactory.instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonIn.getInstance()==SingletonIn.getInstance());
    }
}
