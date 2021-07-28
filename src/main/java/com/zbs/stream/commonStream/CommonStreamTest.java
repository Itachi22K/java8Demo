package com.zbs.stream.commonStream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JDK1.8工作中最常用的14个Stream详细小示例
 * description: CommonStreamTest
 * date: 2020/7/31 16:40
 * author: zhangbs
 * version: 1.0
 */
public class CommonStreamTest {

    /**
     * forEach 遍历
     */
    @Test
    public void forEachTest() {
        Collection<String> strCollection = Arrays
                .asList("you", "don`t", "bird", "me", ",", "i", "don`t", "bird", "you");
        strCollection.stream().forEach(System.out::println);
        //foreach 无返回值
//        strCollection.stream().forEach(s->return s+1;);
    }

    /**
     * filter 过滤【filter 过滤对象必须新对象接收，否则原对象是没有被更改的】
     */
    @Test
    public void filterTest() {
        List<User> userList = Arrays.asList(
                new User(1L, "mengday", 28, new BigDecimal(11)),
                new User(2L, "guoguo", 18, new BigDecimal(20)),
                new User(3L, "liangliang", 17, new BigDecimal(33))
        );
        System.out.println(userList.stream().filter(user -> user.getMoney().compareTo(new BigDecimal(18)) == 1).map(User::getMoney).reduce(BigDecimal::add));

        BigDecimal bb = BigDecimal.valueOf(15);
        System.out.println(bb.toString());
        String cc = bb.toString();
        System.out.println(cc);

//        userList.stream().filter(user -> user.getAge() > 18).forEach(System.out::println);
//        List<User> userList2 = userList.stream().filter(user -> user.getAge() > 18).collect(Collectors.toList());
//
//        System.out.println("=======分割输出======");
//        Consumer<User> consumer = System.out::println;
//        for (User user : userList) {
//            consumer.accept(user);
//        }
//
//        System.out.println("=======分割输出======");
//        for (User user : userList2) {
//            consumer.accept(user);
//        }
    }

    /**
     * map 映射对象，更改对象【map 映射转换对象必须新对象接收，否则原对象是没有被更改的】
     */
    @Test
    public void mapTest() {
        List<String> list = Arrays.asList("how", "are", "you", "how", "old", "are", "you", "?");
        list.stream().map(str -> {
            str += "_zbs";
            return str.toUpperCase();
        }).forEach(System.out::println);
        System.out.println("========map后的list=======");
        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     * flatMap 将两个对象进行拼接结合
     */
    @Test
    public void flatMapTest() {
        List<Integer> list1 = Arrays.asList(1, 3, 5);
        List<Integer> list2 = Arrays.asList(2, 4, 6);

        List<List<Integer>> list3 = new ArrayList<>();
        list3 = Stream.of(list1, list2).collect(Collectors.toList());
        // [[1,3,5],[2,4,6]]
        list3.forEach(System.out::println);

        // 将多个集合中的元素合并成一个集合
        Stream.of(list1, list2).flatMap(list -> list.stream())
                .collect(Collectors.toList())
                .forEach(System.out::print);

        System.out.println();
        System.out.println("-------排序后的结果------");
        Stream.of(list1, list2).flatMap(list -> list.stream())
                .collect(Collectors.toList())
                .stream()
                .sorted((x, y) -> x - y).forEach(System.out::print);
        System.out.println();
        Stream.of(list1, list2).flatMap(list -> list.stream())
                .collect(Collectors.toList())
                .stream()
                .sorted((x, y) -> x.compareTo(y)).forEach(System.out::print);
    }

    /**
     * sorted排序
     */
    @Test
    public void sortedTest() {
//        List<String> list = Arrays.asList("c", "e", "a", "d", "b");
        //list是空数组，下边的排序s1，s2也不会报空指针
        List<String> list = new ArrayList<>();
        // Stream<T> sorted(Comparator<? super T> comparator);
        // int compare(T o1, T o2);
        list.stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::println);
    }

    /**
     * distinct,流去重
     */
    @Test
    public void distinctTest() {
        List<String> list = Arrays.asList("know", "is", "know", "noknow", "is", "noknow");
        list.stream().distinct().forEach(System.out::print);
    }

    /**
     * count 总数量
     */
    @Test
    public void countTest() {
        List<String> list = Arrays.asList("know", "is", "know", "noknow", "is", "noknow");
        long count = list.stream().count();
        System.out.println(count);
    }

    /**
     * min
     * max
     * “函数”和“排序”功能有很大的关系
     */
    @Test
    public void minTest() {
        List<Integer> list = Arrays.asList(3, 5, 7, 2, 9, 11);
        // 2
//        Optional<Integer> min = list.stream().min((x, y) -> x.compareTo(y));
        // 2
//        Optional<Integer> min = list.stream().min((x, y) -> x - y);
        // 11
//        Optional<Integer> min = list.stream().min((x, y) -> y - x);
        // 2
        Optional<Integer> min = list.stream().max((x, y) -> y - x);
        min.ifPresent(System.out::println);
    }

    /**
     * skip
     */
    @Test
    public void skipTest() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        // Stream<T> skip(long n)
        // a、b、c、d、e
        list.stream().skip(0).forEach(System.out::println);
        System.out.println("==========");
        // c、d、e
        list.stream().skip(2).forEach(System.out::println);
        System.out.println("==========");
        // c、d
        list.stream().skip(2).limit(2).forEach(System.out::println);
    }

    /**
     * collect  stream--》list
     * toArray  stream--》Object[]
     */
    @Test
    public void collectTest() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        // Stream -> Collection
        List<String> collect = list.stream().collect(Collectors.toList());

        // Stream -> Object[]
        Object[] objects = list.stream().toArray();
    }

    /**
     * concat：连接两个流中的元素
     */
    @Test
    public void concatTest() {
        List<String> list1 = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("c", "d");
        //[a, b]
        //[c, d]
        Stream<List<String>> listStream = Stream.of(list1, list2);
        listStream.forEach(System.out::println);
        System.out.println("==============");
        // a、b、c、d
        Stream.concat(list1.stream(), list2.stream()).forEach(System.out::println);
    }

    /**
     * anyMatch
     * allMatch
     */
    @Test
    public void matchTest() {
        // 你给我站住
        List<String> list = Arrays.asList("you", "give", "me", "stop");
        // boolean anyMatch(Predicate<? super T> predicate);
        // parallelStream可以并行计算，速度比stream更快
        boolean result = list.parallelStream().anyMatch(item -> item.equals("me"));
        System.out.println(result);
    }

    /**
     * anyMatch伪代码
     * 如果集合中有一个元素满足条件就返回true
     *
     * @return
     */
    public boolean anyMatch() {
        List<String> list = Arrays.asList("you", "give", "me", "stop");
        for (String item : list) {
            if (item.equals("me")) {
                return true;
            }
        }
        return false;
    }

    /**
     * reduce 归纳 + 拼接（求和）
     */
    @Test
    public void reduceTest() {
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        Optional<String> reduceOption = stream.reduce((x, y) -> x + ",," + y);
        reduceOption.ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        List<BigDecimal> list = Arrays.asList(
                new BigDecimal("11.11"),
                new BigDecimal("22.22"),
                new BigDecimal("33.33")
        );
        // 66.66
        BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }

    /**
     * short-circuiting是指：有时候需要在遍历中途停止操作，比如查找第一个满足条件的元素或者limit操作。
     * 在Stream中short-circuiting操作有：anyMatch、allMatch、noneMatch、findFirst、findAny、limit，
     * 这些操作在Sink中都有一个变量来判断是否短路，
     * 比如limit用的是m，match用的是stop，find用的是hasValue。
     */

    /**
     * findFirst:返回第一个
     * findAny：随机返回
     */
    @Test
    public void findTest() {
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        String value = stream.findFirst().get();
        System.out.println(value);

        System.out.println("================");

        Stream<String> stream2 = Stream.of("you", "give", "me", "stop");
        String value2 = stream2.findAny().get();
        System.out.println(value2);
        System.out.println(IntStream.range(0, 100).parallel().findAny());
    }
}

