package com.zou.corejava.ooad.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 建造者模式 jdk8版本
 * 为了分离对象的属性与创建过程
 *
 * 建造者模式是构造方法的一种替代
 *
 * 让开发者指定一些重要的属性,
 * 然后让建造者自己去创建复杂对象，让创建对象与对象属性分离
 *
 * 对开发者隐藏了复杂对象构建细节，降低框架的学习成本和可复用性
 * 如mybatis中有大量的建造器
 *
 * @author: zoulj
 * @date: 2020-04-04 13:46:29
 */
public class Builder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    //泛型方法
    public <U> Builder<T> with(BiConsumer<T, U> biConsumer, U u) {
        modifiers.add(instance -> biConsumer.accept(instance, u));
        return this;
    }

    public T builder() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    public static void main(String[] args) {
        PersonalComputer pc = Builder.of(PersonalComputer::new)
                .with(PersonalComputer::setBrand, "lenovo")
                .with(PersonalComputer::setOs, "Win10")
                .with(PersonalComputer::setCpu, "i5-6300U")
                .with(PersonalComputer::setScreen, "13.3").builder();
        System.out.println(pc);
    }
}
