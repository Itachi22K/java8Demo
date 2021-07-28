package com.zbs.stream.commonStream;

import lombok.Data;

import java.math.BigDecimal;

/**
 * description: User
 * date: 2020/7/31 16:46
 * author: zhangbs
 * version: 1.0
 */
@Data
public class User {
    private Long id;
    private String userName;
    private Integer userId;
    private Integer age;
    private Integer gender;
    private BigDecimal money;
    private String phone;
    private String address;

    public User() {
    }

    public User(Long id, String userName, Integer userId, BigDecimal money) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.money = money;
    }

    public User(Long id, String userName, Integer userId, Integer age, Integer gender, String phone, String address) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }
}
