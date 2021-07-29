package com.zbs.java8.anonymityClass;

/**
 * description: InnerClassDemo1 匿名内部类
 * date: 2020/3/31 15:00
 * author: zbs
 * version: 1.0
 * 链接地址：https://blog.csdn.net/hellocsz/article/details/81974251
 */
public class InnerClassDemo1 {

    /**
     * 对于抽象类，使用匿名内部类
     */
    public static void print1() {
        Father father = new Father() {
            @Override
            public void simplePrint() {
                System.out.println("我是父类");
            }
        };
        father.simplePrint();
    }

    /**
     * 对于接口，使用匿名内部类
     */
    public static void print2() {
        Father2 father2 = new Father2() {
            @Override
            public void print() {
                System.out.println("我是接口");
            }
        };
        father2.print();
    }

    /**
     * 最常用的情况就是在多线程的实现上，因为要实现多线程必须继承Thread类或是继承Runnable接口
     * Thread类的匿名内部类实现
     */
    public static void print3() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread" + i);
                }
            }
        };
        thread.start();
    }

    /**
     * Runnable接口的匿名内部类实现
     */
    public static void print4() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("runnable" + i);
                }
            }
        };
        //Runnable接口内部只有run方法
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void main(String[] args) {
        print1();
        print2();
        print3();
        print4();
    }

}
