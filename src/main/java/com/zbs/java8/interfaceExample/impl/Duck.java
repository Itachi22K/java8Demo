package com.zbs.java8.interfaceExample.impl;

import com.zbs.java8.interfaceExample.DailyAction;
import org.junit.Test;

/**
 * description: Duck
 * date: 2020/9/11 14:06
 * author: zhangbs
 * version: 1.0
 */
public class Duck implements DailyAction {
    @Override
    public void water() {
        System.out.println("鸭鸭喝水");
    }

    @Test
    public void test() {
        Duck duck = new Duck();
        duck.eat();
        duck.water();
        System.out.println("====调用接口的静态方法====");
        DailyAction.test();
    }
}
