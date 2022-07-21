package com.zou.corejava.base.collections;

import java.util.*;

/**
 * Collection 单列集合接口的顶层父类
 * <p>
 * List:有序,有索引,可重
 * Set:无序,不重
 *
 */
public class CollectionTest {
    public static void main(String[] args) {
        //setTest1();
        iterableTest();
    }

    /**
     * Iterable和Iterator的区别
     * <p>
     * Iterable接口是单列集合的顶层父类,实现这个接口的类具有可迭代性
     * 里面有Iterator()方法用于迭代遍历集合元素
     * <p>
     * 意义在于创建新的Iterable不用担心Iterator指针问题,互不影响
     */
    public static void iterableTest() {
        List<String> list = Arrays.asList("abc", "bc", "efg", "def", "jkl");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }

        //在迭代器遍历的时候不要去添加删除元素，会有并发问题同时操作
//        ListIterator lit = list.listIterator();
//        while(lit.hasNext()){
//            if("def".equals(lit.next())){
//                lit.add("xyz");
//            }
//        }
    }


    /**
     * Comparable:类的自然排序,类继承此接口,重写compareTo方法
     * Comparator:类的比较器,在集合外部再进行一次比较逻辑
     * 用法如再TreeSet的构造方法入参中，添加比较器的匿名函数即可自动排序
     * HashSet常用作数据检索
     */
    public static void setTest1() {
        //由于优先级的关系,Student类只会根据Comparator中的年龄自行进行比较
        TreeSet ts = new TreeSet(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;// if return<0 then swap
            }
        });
        ts.add(new Student("李四", 20));
        ts.add(new Student("王五", 19));
        ts.add(new Student("阿无", 14));
        ts.add(new Student("李四", 18));
        System.out.println(ts);
    }

}

class Student implements Comparable<Student> {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        int nameResult = this.name.compareTo(o.name);
        return nameResult == 0 ? this.age - o.age : nameResult;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.age;
    }
}
