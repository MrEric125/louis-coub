package com.louis.bootmybatis;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractFuture;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Base64Demo {

    @Test
    public void contextLoads() {




    }

    @Test
    public void test2() {

        String data = "eyJkZXZpY2VJZCI6Ijg2MzE4NTAzMTY2Njc0MSIsImlkZW50aWZ5Q29kZSI6IiIsImludGVyZmFjZUNvZGUiOiJVU0VSX0xPR0lOIiwicGFzc3dvcmQiOiIxMjMiLCJ1c2VyTmFtZSI6IjgwMDA0MDI0IiwidmVyc2lvbiI6IjU4MiJ9";
        byte[] decode = Base64.getDecoder().decode(data);
        String newData = new String(decode);
        System.out.println(newData);

    }

    @Test
    public void encode() {

        String data = "{\"deviceId\":\"863185031666741\",\"interfaceCode\":\"QUERY_IDENTIFY_CODE\",\"password\":\"123\",\"userName\":\"564783\",\"version\":\"663\"}";
//        String data = "{\"deviceId\":\"863185031666741\",\"identifyCode\":\"1826\",\"interfaceCode\":\"USER_LOGIN\",\"password\":\"123\",\"userName\":\"564783\",\"version\":\"663\"}";
//        String data = "{\"deviceId\":\"863185031666741\",\"identifyCode\":\"1826\",\"interfaceCode\":\"USER_HEARTBEAT\",\"password\":\"123\",\"userName\":\"564783\",\"version\":\"663\"}";
        byte[] encode = Base64.getEncoder().encode(data.getBytes());
        String newData = new String(encode);
        System.out.println(newData);

    }


    @Test
    public void getInterface() {
        String data = "{\"deviceId\":\"863185031666741\",\"interfaceCode\":\"QUERY_IDENTIFY_CODE\",\"password\":\"123\",\"userName\":\"564783\",\"version\":\"663\"}";
        JSONObject jsonObject = JSONObject.parseObject(data);
        String interfaceCode = (String) jsonObject.get("interfaceCode");
        String deviceId = jsonObject.get("deviceId").toString();
        deviceId.toCharArray();
        System.out.println(deviceId);

        System.out.println(interfaceCode);


    }

    private Map<String, Integer> immutableMap = ImmutableMap.of( "zhangsan", 1, "lisi",1);
    @Test
    public void ImmutableMapTest() {
        boolean b=null != immutableMap.get("wangwu");



        if (b) {
            System.out.println("sssss");
        }
    }

}
