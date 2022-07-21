package com.zou.corejava.base.reflect;


public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public void say(String str) {
        System.out.println(str + " my name is " + name + " and " + age + " year's old");
    }

    static void run(String str) {
        System.out.println(str + " static run....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
