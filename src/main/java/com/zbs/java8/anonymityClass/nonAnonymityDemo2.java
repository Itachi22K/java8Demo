package com.zbs.java8.anonymityClass;

/**
 * description: nonAnonymityDemo2
 * date: 2020/3/31 15:05
 * author: zbs
 * version: 1.0
 */
public class nonAnonymityDemo2 {
    public static void main(String[] args) {
        Father father=new Son();
        /**
         * 子类实例向上转型为父类的引用，
         * 父类引用具有子类的特点。
         */
        //打印：我是子类
        father.simplePrint();
    }
}
