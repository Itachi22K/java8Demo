package com.zbs.test;

/**
 * description: toStringDemo
 * date: 2020/9/25 17:32
 * author: zhangbs
 * version: 1.0
 */
public class ToStringDemo {
    static class A {
        @Override
        public String toString() {
            System.out.print("I ");
            return "返回值";
        }
    }

    public static void main(String[] args) {
        // 在IED里应该可以看见a的值，每显示一次，IDE都会调用一次toString()方法； 如果你多打几个断点调试，还会打印更多的"I"
        A a = new A();
        System.out.println("love you." + a);
    }
}
