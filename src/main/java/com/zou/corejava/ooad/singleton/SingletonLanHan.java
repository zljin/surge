package com.zou.corejava.ooad.singleton;

public class SingletonLanHan {


    //添加volatile禁止指令重排序
    private static volatile SingletonLanHan instance = null;

    private SingletonLanHan() {
    }

    //会报错
    public static SingletonLanHan getInstance() {
        if (instance == null) {
            synchronized (SingletonLanHan.class) {
                if (instance == null) {
                    instance = new SingletonLanHan();
                    /*报错原因:
                     * instance = new Singleton2();
                     * 创建对象和赋值操作是分开的
                     * 1.new对象,分配内存
                     * 2.调用构造函数初始化成员变量
                     * 3.将instance指向分配的内存空间
                     *
                     * JVM存在乱序执行功能
                     *
                     * 若上面的执行顺序为1-3-2会出现问题
                     * 当instance!=null的时候得到的是一个
                     * 未初始化的对象
                     *
                     *
                     * 解决方案：添加volatile禁止指令重排序
                     * */
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonLanHan.getInstance() == SingletonLanHan.getInstance());
    }
}
