package com.zou.corejava.ooad.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 代理类：创建一个中介对象,外界访问通过代理的方式去访问真正的对象
 * 好处是可以增强这个类，然后保护真正对象的安全,松耦合等特点
 * 静态代理：在启动之前静态代理就已经创建了,缺点是不用的时候占内存，不够灵活不推荐
 * 动态代理：在启动时通过反射和入参去动态创建代理对象,使用就创建,不使用就不创建,灵活且方便
 * <p>
 * jdk代理：目标对象依赖与接口
 * cglib代理：不需要依赖接口,依赖类即可
 * <p>
 * java.lang.reflect.InvocationHandler（处理器接口）
 * java.lang.reflect.Proxy:生成动态代理类和对象
 */
public class JdkProxyObject implements InvocationHandler {

    private Object obj;

    public JdkProxyObject(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法的增强 front");
        method.invoke(obj, args);
        System.out.println("方法的增强 end");
        return null;
    }

    public static void main(String[] args) {
        Subject subject = new childSubject();
        JdkProxyObject proxyObject = new JdkProxyObject(subject);
        Subject proxyInstance = (Subject) Proxy.
                newProxyInstance(ClassLoader.getSystemClassLoader(),
                        new Class[]{Subject.class},
                        proxyObject);

        proxyInstance.work();
    }
}

interface Subject {
    void work();
}

class childSubject implements Subject {

    @Override
    public void work() {
        System.out.println("work....");
    }
}