package com.zou.corejava.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author leonard
 * @Description  jdk8常见用法
 *
 * (1) ::符号
 * java8中用作方法引用
 *      它提供了一种不执行方法的方法。
 *      方法引用需要由兼容的函数式接口组成的目标类型上下文
 *
 * (2) lambda:本质：匿名内部类的简化
 *        入参依赖方法引用,且局部变量默认为final
 *
 * (3) Stream用法
 * 获得流对象()                 中间操作                  最终操作
 * Collection.stream(),                                    orElse(null)
 * +--------------------+       +------+   +------+       +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-----> |map+-> |collect|
 * +--------------------+       +------+   +------+       +---+   +-------+
 *
 * (4) 函数式接口
 *      编程思想转变：行为参数化,在执行前,将行为准备好
 * 1. 有且只有一个抽象方法的接口 @FunctionalInterface
 * 2. 函数式接口->匿名内部类(或者实现了函数式接口的类)->lambda
 *
 * (5) Optional
 *      一个存放值的容器,常用于优雅解决空指针异常
 *      java.util.Optional#orElse(java.lang.Object)
 *      java.util.Optional#isPresent()
 *      java.util.Optional#get()
 *
 * @date 2021-07-30 11:32
 */
public class Jdk8AAAAATest {

    private static final List<Book> arrayList = Arrays.asList(
            new Book("zs", 101.1, "a","文学"),
            new Book("ls", 31.1, "b","计算机"),
            new Book("ww", 41.1, "c","科幻小说"),
            new Book("wyf", 51.1, "d","科学"),
            new Book("lh", 82.9, "e","科幻小说"),
            new Book("zyx", 71.1, "f","计算机"),
            new Book("hzt", 81.1, "g","科幻小说"),
            new Book("zlj", 91.1, "h","文学")
    );


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class Book{
        private String name;
        private Double price;
        private String author;
        private String type;
    }

    @Test
    public void testStream() {

        //.parallel() 自动使用并行处理,提高执行效率
        List<Book> filterList = Optional.ofNullable(arrayList.stream().parallel()
                .filter(a -> a.getPrice() > 50)
                .collect(Collectors.toList()))
                .orElse(new ArrayList<>());

        List<Book> sortedList = arrayList.stream()
                .sorted(Comparator.comparing(Book::getPrice).reversed())
                .collect(Collectors.toList());

        sortedList.stream().forEach(System.out::println);//a->sout(a)

        // todo reduce使用
        //处始化值为0再,累加
        double totalNum = arrayList.stream().map(Book::getPrice).reduce(0.0,(a, b)->a+b);
        System.out.println(totalNum);

        Optional<Double> reduce = arrayList.stream().map(Book::getPrice).reduce((a, b) -> a + b);
        if(reduce.isPresent()){
            System.out.println(reduce.get());
        }

        // todo 分组输出,根具Book.type进行分组
        Map<String,List<Book>> typeGroup = Optional.ofNullable(arrayList.stream()
                .collect(Collectors.groupingBy(Book::getType,Collectors.toList()))).orElse(new HashMap<>());

        typeGroup.forEach((k,v)->{
            System.out.println(k+"====>"+v);
        });

        //List 转数组
        Book[] books1 = arrayList.stream().toArray(Book[]::new);

        //List转Map
        Map<String, String> map1 = arrayList.stream()
                .collect(Collectors.toMap(Book::getName, Book::getType));

        map1.forEach((k,v)-> System.out.println(k+"===>"+v));


        //多集合相加
        //flatMap 将所有的集合转换为对应的stream流
        List<Book> totalList = Stream.of(filterList, sortedList).flatMap(Collection::stream).collect(Collectors.toList());
        totalList.stream().forEach(System.out::println);
    }


    @Test
    public void dateAAATest(){
        //日期
        LocalDate today  = LocalDate.now();
        LocalDate firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1);
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());


        //日的
        LocalDateTime startTime1 = LocalDateTime.of(firstDay, LocalTime.MIN);
        LocalDateTime endTime1 = LocalDateTime.of(firstDay, LocalTime.MAX);
        System.out.println(startTime1);
        System.out.println(endTime1);

        //周的第一天和最后一天

        LocalDate newLocalDate = today.minusWeeks(1l)
                .with(DayOfWeek.SUNDAY);
        LocalDate newLocalDate2 = today.with(DayOfWeek.SATURDAY);


        System.out.println("本周第一天："+newLocalDate);
        System.out.println("本周最后一天："+newLocalDate2);

        //月的第一天和最后一天
        LocalDateTime startTime = LocalDateTime.of(firstDay, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(lastDay, LocalTime.MAX);
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void testJdk8Date(){
        //todo Instant时刻
        // 跟时区无关的时间搓 及UTC 0 时区
        Instant now = Instant.now();
        //Instant转Date ,会自动获取当前时区进行相加
        Date nowDate = Date.from(now);
        //todo LocalDate 处理日期的 可对比Calendar
        LocalDate now1 = LocalDate.now();
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        int dayOfYear = LocalDate.now().getDayOfYear();
        System.out.println(dayOfMonth);
        System.out.println(dayOfWeek.getValue());

        //todo localTime 处理时间的
        System.out.println(LocalTime.now().getMinute());
        System.out.println(LocalTime.now().getSecond());

        //todo ZonedDateTime 处理时区的
        Date currnt = new Date();
//todo  Date-----> LocalDateTime 这里指定使用当前系统默认时区
        LocalDateTime localDateTime = currnt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//todo  LocalDateTime------> Date 这里指定使用当前系统默认时区
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(currnt);
        System.out.println(currnt.toInstant());
        System.out.println(currnt.toInstant().atZone(ZoneId.systemDefault()));

    }

}
