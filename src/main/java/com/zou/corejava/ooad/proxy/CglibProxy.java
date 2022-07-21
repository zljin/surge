package com.zou.corejava.ooad.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 */
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("before-------切面加入逻辑");
        methodProxy.invokeSuper(object, args);
        System.out.println("after-------切面加入逻辑");
        return null;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Base.class);
        //回调方法的参数为代理类对象CglibProxy,最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
        enhancer.setCallback(proxy);
        //此刻，base是增强过的目标类
        Base base = (Base) enhancer.create();
        base.add();
        base.minu();
    }
}

class Base{

    public void add(){
        System.out.println("目标类add方法");
    }

    /*
    cglib通过继承的方式来实现动态代理
    若为final修饰类或者方法则无法实现增强
    */
    public final void minu(){
        System.out.println("目标类minu方法");
    }
}