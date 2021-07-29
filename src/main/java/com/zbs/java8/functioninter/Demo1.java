package com.zbs.java8.functioninter;

/**
 * description: Demo1 函数式接口，Lambda表达式练习
 * 函数式接口作为参数
 * date: 2020/4/6 18:28
 * author: zbs
 * version: 1.0
 */
public class Demo1 {
    public static void startThread(Runnable runnable){
        new Thread(runnable).start();
    }

    public static void main(String[] args) {
        /**
         * 使用接口匿名内部类
         */
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程开启");
            }
        });
        /**
         * 使用Lambda表达式
         */
        startThread(()-> System.out.println(Thread.currentThread().getName()+"线程开启"));
    }
}
