package com.zbs.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * description: StreamTest
 * date: 2020/7/28 14:03
 * author: zhangbs
 * version: 1.0
 * 文章：https://blog.csdn.net/xxssyyyyssxx/article/details/50148169
 * 最常用的14个Stream：https://www.toutiao.com/i6805004400730309134/
 * JSK1.8文档
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
//        InterfaceEntry interfaceEntry = System.out::println;
        // filter过滤、forEach遍历
        //stringCollection.stream().filter((s) -> s.startsWith("b")).forEach(System.out::println);
//        List<String> b = stringCollection.stream().filter((s) -> s.startsWith("b")).collect(Collectors.toList());
//        System.out.println(b.toString());

        // sorted进行排序
        //stringCollection.stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::println);
        //stringCollection.stream().sorted(String::compareTo).forEach(System.out::println);

        //Map 映射转换对象
//        stringCollection.stream().map(s -> s + "_zbs").forEach(System.out::println);
//        stringCollection.stream().map(s -> s + "_zbs").collect(Collectors.toList()).forEach(System.out::println);
//        stringCollection.stream().map(s -> s + "_zbs");
//        System.out.println(stringCollection.get(1));

        //Match 匹配
//        testMatch(stringCollection);

        //count计数
        long num = stringCollection.stream().filter(s -> s.startsWith("b")).count();
        System.out.println(num);
    }

    public static void testMatch(List<String> stringCollection) {
        //Match 匹配
        // 任意匹配
        boolean anyStartsWithA = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStartsWithA);
//        // 全部匹配
//        boolean allStartsWithB = stringCollection.stream().allMatch(s -> s.startsWith("a"));
//        System.out.println(allStartsWithB);
//        // 不匹配
//        boolean noneStartsWithC = stringCollection.stream().noneMatch(s -> s.startsWith("a"));
//        System.out.println(noneStartsWithC);
    }

    @Test
    public void testOptional() {
        /**
         * 解读JDK8中的Optional.of和Optional.ofNullable方法的区别和用法
         * https://blog.csdn.net/qq_40223688/article/details/104264461
         * Optional.of(T value) 当value为空的时候，会报NullPointerException
         * 所以，建议使用：
         *      Optional.ofNullable().orElse(value)
         *      Optional.ofNullable().orElseThrow(new Exception());
         */

        // Optional 接口
        List<String> collection = new ArrayList<>();
        // 判断是否为null，存在：true
        System.out.println(Optional.of(collection).isPresent());

        // 判断是否为空，不是空就赋值，是空就赋值else得内容
        collection = null;
        List<String> collectionResult;
        collectionResult = Optional.ofNullable(collection).orElse(Arrays.asList("collection是null"));
        System.out.println(collectionResult);

        // ifPresent[if (value != null) consumer.accept(value)]
        Optional<String> optional = Optional.of("bam");
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }

    /**
     * Supplier：无输入、有返回
     */
    @Test
    public void testSupplier() {
        // 示例1
        int num1 = 100;
        int num2 = 200;
        // 提前定义好需要返回的指定类型结果，但不运行
        Supplier<Integer> supplier = () -> num1 + num2;
        // 调取get()方法获取一个结果
        System.out.println(supplier.get());
        //【Supplier：供给型函数式接口使用了(局部变量)num1】num1默认为final，不可以更改
        //num1 = 300;
    }

    /**
     * Consumer：有输入、无返回
     */
    @Test
    public void testConsumer() {
        // 示例1
        int num1 = 100;
        // 提前定义好需要返回的指定类型结果，但不运行
        Consumer<Integer> consumer = (num) -> System.out.println((num1 + num));
        consumer.accept(10);
    }

    /**
     * Function 有输入、有输出
     */
    @Test
    public void testFunction() {
        // 示例1
        // 提前定义好需要返回的指定类型结果，但不运行
        Function<Integer, Integer> function = num -> {
            int reNum = num + 1;
            return reNum;
        };
        System.out.println(function.apply(100));
    }

    @Test
    public void testPredicate() {
        // 示例1
        // 提前定义好需要返回的指定类型结果，但不运行
        Predicate<Integer> predicate = num -> num > 0;
        System.out.println(predicate.test(100));
    }

    /**
     * Predicate
     *
     * @param one
     * @param two
     */
    private void method(Predicate<String> one, Predicate<String> two) {
        /**
         * 源码
         * default Predicate<T> or(Predicate<? super T> other) {
         *         Objects.requireNonNull(other);
         *         return (t) -> test(t) || other.test(t);
         *     }
         */
        boolean isVariable = one.or(two).test("helloWorld");
        System.out.println(isVariable);
    }

    @Test
    public void testOr() {
        method(s -> s.contains("H"), s -> s.contains("W"));
    }

    /**
     * andThen 执行完第一步，执行第二步
     *
     * @param one
     * @param two
     */
    private void consumeString(Consumer<String> one, Consumer<String> two) {
        one.andThen(two).accept("Hello");
    }

    @Test
    public void testAndThen() {
        consumeString(
                // HELLO
                s -> System.out.println(s.toUpperCase()),
                // Hello  s并没有因为上一部而改变s的值
                s -> System.out.println(s)
        );
    }

    /**
     * Compose：按顺序执行多条语句
     *
     * @param a
     * @param function1
     * @param function2
     * @return
     */
    public int composeTest(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        System.out.println(a);
        int b = function1.compose(function2).apply(a);
        System.out.println(a);
        return b;
    }

    @Test
    public void testCompose() {
        System.out.println(composeTest(2, value -> value * 3, value -> value * value));
    }

    @Test
    public void testList() {
        List list = new ArrayList();
        list.add(5);

        for (int i = 0; i <= list.size() - 2; i++) {
            // 不满足i <= list.size() - 2，则不会进入循环
            System.out.println(list.get(i));
            System.out.println(list.get(i + 1));
        }
        System.out.println("dddddd");
    }
}
