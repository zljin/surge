package com.zou.corejava.base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * 反射：
 * 在运行时能够动态的获取类的信息和动态调用对象的方法和属性的能力
 * <p>
 * 由较好的灵活性使用于各种框架，但破坏了类的封装性，有性能损耗
 *
 * @author lingjin.zou
 * @Date 2022-02-06
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //reflectTest01();
        reflectTest02();
    }

    /**
     * 基于注解+反射模拟Spring的autowired
     * @throws Exception
     */
    public static void reflectTest02() throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> aClass = userController.getClass();
        //获取所有属性值
        Stream.of(aClass.getDeclaredFields()).forEach(filed->{
            AutoWired2 annotation = filed.getAnnotation(AutoWired2.class);
            if(annotation!=null){
                filed.setAccessible(true);
                //获取属性的类型
                Class<?> type = filed.getType();
                try {
                    Object o = type.newInstance();
                    filed.set(userController,o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }

    /**
     * 反射的基础使用
     * @throws Exception
     */
    public static void reflectTest01() throws Exception {
        //1. 获取类镜像
        Class<?> aClass = Class.forName("com.zoulj.base.reflect.Person");

        //2. 动态获取类构造器
        Constructor<?> constructor = aClass.getConstructor(String.class, int.class);

        //3. 通过类构造器创建对象
        Object obj = constructor.newInstance("leonard", 24);

        //4. 通过类镜像获取其成员变量
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);//暴力访问
        name.set(obj, "kawhi");

        //5. 通过类镜像获取成员方法
        Method method = aClass.getMethod("say", String.class);
        method.invoke(obj, "hello");

        //6. 通过类镜像获取静态方法
        Method staticMethodSay = aClass.getDeclaredMethod("run", String.class);
        staticMethodSay.invoke(null, "lady");
    }
}
