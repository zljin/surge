package com.zou.corejava.base.reflect;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

//元注解介绍
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期
@Target({ElementType.FIELD, ElementType.METHOD})//注解的作用目标
@Documented//注解是否应当被包含在 JavaDoc 文档中
@Indexed//是否允许子类继承该注解
public @interface AutoWired2 {

}
