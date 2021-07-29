package com.zbs.java8.functioninter;

/**
 * description: InterfaceEntry 接口入门
 * date: 2020/3/31 14:13
 * author: zbs
 * version: 1.0
 * 函数式接口：有且仅有一个抽象方法 public abstract。[接口的修饰符是”public abstract“，通常”abstract“可以省略（默认就是这个类型）]
 * 从jdk1.8开始，接口可以有其他的静态方法、默认方法、私有方法。
 *
 * 问题：函数式接口的好处和坏处？
 *      貌似：接口仅限于一个会不够用。
 *
 * @FunctionalInterface：检测接口是否是一个函数式接口
 *                      是：编译成功
 *                      否：编译失败（1、接口中没有抽象方法；2、接口中有多个抽象方法）
 */
@FunctionalInterface
public interface InterfaceEntry {

    /**
     * 定义一个抽象方法
     */
    public abstract void simplePrint();

    //public abstract void simplePrint2();
}
