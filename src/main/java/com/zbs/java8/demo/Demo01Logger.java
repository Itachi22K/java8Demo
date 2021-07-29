package com.zbs.java8.demo;

/**
 * description: Demo01Logger  日志案例
 * date: 2020/4/6 18:00
 * author: zbs
 * version: 1.0
 *
 * 性能浪费的问题：
 * msg参数先进行拼接，再调用方法，
 * 在level级别是1的情况下才会打印，
 * 否则msg即使被创建了，也不会被打印，就浪费了
 */
public class Demo01Logger {
    private static void log(int level, String msg) {
        if (level == 1) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        String msgA = "Hello";
        String msgB = "World";
        String msgC = "Java";
        log(1, msgA + msgB + msgC);
    }
}

