package com.zbs.java8.functioninter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description: Demo2 函数式接口作为返回值
 * date: 2020/4/6 18:49
 * author: zbs
 * version: 1.0
 */
public class Demo2 {
    public static Comparator<String> getComparable() {
        /**
         * 通过匿名内部类返回
         */
        //默认增序
//        return new Comparator<String>() {
//            @Override
//            public int compare(String str1, String str2) {
            // 如果str1要比str2大，则交换位置，所以是升序
        /**
         * 源码如下：Arrays：1543行
         *  for (int i=low; i<high; i++)
         *      for (int j=i; j>low && c.compare(dest[j-1], dest[j])>0; j--)
         *       swap(dest, j, j-1); //交换位置
         *     return;
         */
//                return str1.length() - str2.length();
//            }
//        };

        /**
         * 使用Lamdba表达式
         */
//        return (String str1, String str2) -> {
////            return str1.length() - str2.length();
////        };

        /**
         * 优化Lambda表达式
         */
        return (str1, str2) -> str1.length() - str2.length();
    }

    public static void main(String[] args) {
        String[] arr = {"aa", "bbbb", "c", "dddddddd"};
        //打印数组
        System.out.println(Arrays.toString(arr)); //[aa, bbbb, c, dddddddd]
        //自定义排序
        Arrays.sort(arr, getComparable());
        System.out.println(Arrays.toString(arr)); //[c, aa, bbbb, dddddddd]
    }

}
