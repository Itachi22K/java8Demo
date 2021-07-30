package com.zbs.test;

import com.zbs.common.Constants;
import com.zbs.java8.stream.commonStream.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * description: Java8Api
 * date: 2020/7/6 15:12
 * author: zhangbs
 * version: 1.0
 */
public class JavaDemoTest {

    public static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= Constants.TEN; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }

    @Test
    public void test() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("张", "大宝");
        System.out.println(person.firstName + "  " + person.lastName);

        Consumer<Person> consumer = System.out::print;
        consumer.accept(person);
    }

    @Test
    public void filter() {
        List<User> userData = JavaDemoTest.getUserData();
        userData.stream().filter(
                user ->
                        user.getGender().equals(0) && user.getAge() > 50
        ).forEach(e -> System.out.println(e));
    }

    @Test
    public void peek() {
        List<User> userData = JavaDemoTest.getUserData();
        userData.stream().forEach(
                user -> {
                    if (user.getAge() > 50) {
                        System.out.println(user);
                    }
                }
        );
    }

    @Test
    public void testObjectList() {
        List<User> userData = JavaDemoTest.getUserData();
//        userData.stream().forEach(
//                user -> System.out.println(user.toString())
//        );
        userData.get(userData.size() - 1).setUserName("我被改变了");
        System.out.println("------------" + userData.get(userData.size() - 1));
        userData.stream().forEach(
                user -> System.out.println(user.toString())
        );

        User user1 = userData.get(userData.size() - 1);
        user1.setUserName("对象更改，我被改变了，第二次");
        System.out.println("------------" + userData.get(userData.size() - 1));
        userData.stream().forEach(
                user -> System.out.println(user.toString())
        );
    }

    @Test
    public void testIntList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 7, 9);
        list.stream().forEach(
                i -> System.out.println(i)
        );
        Integer jk = list.get(list.size() - 1);
        jk = 100;
        System.out.println(list);
    }

}
