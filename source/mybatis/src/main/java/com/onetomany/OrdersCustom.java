package com.onetomany;

import lombok.Data;

@Data
public class OrdersCustom extends Orders {
    // 添加用户的属性
    private String username;
    private String sex;
    private String address;
        // getter and setter......
}