package com.zbs.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbs.java8.stream.commonStream.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

/**
 * description: FunctionTest2
 * date: 2020/7/30 14:22
 * author: zhangbs
 * version: 1.0
 */
public class TestDemo {

    @Test
    public void testSplit() {
        List<String> strList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //s -> "[]"
            String s = objectMapper.writeValueAsString(strList);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void consumeString(Consumer<String> one, Consumer<String> two) {
        one.andThen(two).accept("Hello");
    }

    @Test
    public void testAndThen() {
        consumeString(
                s -> System.out.println(s.toUpperCase()),
                s -> System.out.println(s)
        );
    }

    @Test
    public void testString() {
        // String str1 = "[]"; // 不会报异常
        String str = ""; // 会报异常
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> tableSonComponentList = new ArrayList<>();
        try {
            tableSonComponentList = objectMapper.readValue(str,
                    new TypeReference<List<User>>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(tableSonComponentList);
    }

    @Test
    public void testObjectUtils() {
        String s2 = "  ";
        System.out.println(s2.length());

//        ObjectUtils 是指springFrameWork的包
//        // 是空
//        String s1 = "";
//        if (!ObjectUtils.isEmpty(s1)) {
//            System.out.println("s1不是空");
//        }
//        // 不是空
//        String s2 = "  ";
//        if (!ObjectUtils.isEmpty(s2)) {
//            System.out.println("s1不是空");
//        }
    }

    /**
     * replaceAll、replace 必须用新的子浮串接收才能获取新的替换值
     */
    @Test
    public void testReplace() {
        String str = "2018.05.12.18";
        String s = str.replaceAll("[\\pP\\pS\\pZ\\pC]", "-");
        System.out.println(s);

        str.replaceAll("18", "-");
        System.out.println(str);

        str.replace("18", "-");
        System.out.println(str);

//        System.out.println(str.contains("."));
    }

    @Test
    public void testMapToString() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "sdff");
        map.put("2", "dasd");
        // {1=sdff, 2=dasd}
        System.out.println(map.toString());
    }

    @Test
    public void testSplitDemo() {
        String str = "55.99";
        String[] split = str.split("");
        // length = 5
        System.out.println(split.length);
    }

    @Test
    public void Test1() {
        String sort = "1,2,3,4,5,";
        if (sort.endsWith(",")) {
            sort = sort.substring(0, sort.length() - 1);
        }
        // 1,2,3,4,5
        System.out.println(sort);
    }

    @Test
    public void Test2() {
        System.out.println(LocalDate.now().plusDays(-1));
    }

    @Test
    public void Test3() {
        String ss = "333";
        System.out.println(Optional.ofNullable(ss).orElse(test4(ss)));

    }

    private String test4(String ss) {
        System.out.println("是否执行了，" + ss);
        return "dddd";
    }

    @Test
    public void test6() {
        System.out.println(test5());
    }

    public int test5() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
//        Date date = new Date();
//        return date.getHours();
    }

    @Test
    public void test7() {
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("a"));
        // 只能用迭代器进行操作
        /**
         * 迭代器操作
         * .next()必须必须.remove()之前调用。在foreach循环中，编译器会.next()在删除元素的操作后进行调用，从而导致
         * ConcurrentModificationException。
         */
        Iterator<String> iter = stringList.iterator();
        while (iter.hasNext()) {
            // iter.next()指向数组的第一个位置的值
            String s = iter.next();

            if (s.equals("a")) {
                iter.remove();
            }
        }
        /**
         * 2、foreach循环
         */
//        for (String s : stringList) {
//            // 删除元素后，列表的大小会缩小，索引也会更改。因此，如果要使用索引删除循环内的多个元素，则无法正常工作。
//            // java.util.ConcurrentModificationException
//            if ("b".equals(s)) {
//                stringList.remove(s);
//            }
//        }
        /**
         * 3、for循环
         */
//        for (int i = 0; i < stringList.size(); i++) {
//            // 如果有取值比较，无论是按位置删除还是按元素删除，都是没有问题的
//            if ("b".equals(stringList.get(i))) {
//                // 最终结果是[a, c, d, b]
//                stringList.remove(stringList.get(i));
//
//                // 最终结果是[a, b, c, d]
////                stringList.remove(i);
//            }
//        }
        System.out.println(stringList.size());
        System.out.println(stringList);
    }

}

