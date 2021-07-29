package com.zbs.java8.interfaceExample;

/**
 * 接口：可以有实现，可以没实现；有点像抽象类
 * 有实现的方法，必须有“default”或者是“static”
 * description: dailyAction
 * date: 2020/9/11 14:01
 * author: zhangbs
 * version: 1.0
 */
public interface DailyAction {

    static void test() {
        System.out.println("接口静态方法");
    }

    /**
     * default 声明方法可以写默认的方法体，实现接口时，默认有实现
     * 此方法，可以实现，可以不实现
     */
    default void eat() {
        System.out.println("动物吃食物");
    }

    void water();

}
