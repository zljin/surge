package com.zou.corejava.jdk8;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 编程思想的转变:1.将行为参数化2.在执行前,将行为提前准备好
 * 有且只有一个抽象方法的接口 @FunctionalInterface
 * 函数式接口->匿名内部类(或者实现了函数式接口的类)->lambda
 */
public class FunctionalAAATest {
    public static int[] Intarr = {2, 3, 4, 52, 333, 23};
    public static String[] Stringarr = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};
    String str = "赵丽颖,100";

    public static int getMax(int[] a, Supplier<Integer> supplier) {
        return supplier.get();
    }

    public static void printInfo(String[] arrays, Consumer<String> one, Consumer<String> two) {
        for (String info : arrays) {
            one.andThen(two).accept(info);
        }
    }

    public static List<String> filter(String[] arrays, Predicate<String> one, Predicate<String> two) {
        List<String> resultList = new ArrayList<>();
        for (String info : arrays) {
            if (one.and(two).test(info)) {
                resultList.add(info);
            }
        }
        return resultList;
    }

    public static int functionNum(String str, Function<String, String> one, Function<String, Integer> two, Function<Integer, Integer> three) {
        return one.andThen(two).andThen(three).apply(str);
    }

    @Test
    public void testOne() {//找到数组中最大值
        int max = getMax(Intarr, () -> {
            int re = 0;
            for (int i : Intarr) {
                if (re < i) {
                    re = i;
                }
            }
            return re;
        });
        System.out.println(max);
    }

    @Test
    public void testTwo() {//请按照格式  姓名：XX。性别：XX。 ”的格式将信息打印出来
        printInfo(Stringarr,
                s -> System.out.print("姓名：" + s.split("[,]")[0]),
                s -> System.out.println(" 年龄：" + s.split("[,]")[1]));
    }

    @Test
    public void testThree() {//集合筛选 1. 必须为女生； 2. 姓名为4个字。
        List resultList = filter(Stringarr,
                s -> s.split("[,]")[0].length() == 4,
                s -> s.split("[,]")[1].equals("女"));
        System.out.println(resultList);
    }

    @Test
    public void testFour() {//将字符串的年龄加100
        int result = functionNum(str, s -> s.split("[,]")[1], s -> Integer.parseInt(s), s -> s + 100);
        System.out.println(result);
    }
}

