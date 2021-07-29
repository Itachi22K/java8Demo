package com.zbs.java8.interfaceExample.impl;

import com.zbs.java8.interfaceExample.DailyAction;
import org.junit.Test;

/**
 * description: Duck
 * date: 2020/9/11 14:06
 * author: zhangbs
 * version: 1.0
 */
public class Tiger implements DailyAction {

    /**
     * 重写了eat方法
     */
    @Override
    public void eat() {
        System.out.println("动物【虎虎】吃食物");
    }

    @Override
    public void water() {
        System.out.println("虎虎喝水");
    }

    @Test
    public void test() {
        Tiger tiger = new Tiger();
        tiger.eat();
        tiger.water();
        System.out.println("====调用接口的静态方法====");
        DailyAction.test();
    }
}
