package com.zbs.java8.interfaceExample;

/**
 * description: abstractExample
 * date: 2020/9/11 14:26
 * author: zhangbs
 * version: 1.0
 */
public abstract class AbstractExample {

    /**
     * 非抽象方法
     */
    void eat() {
        System.out.println("动物吃食物");
    }

    /**
     * 抽象方法，需要abstract修饰
     */
    abstract void water();
}
