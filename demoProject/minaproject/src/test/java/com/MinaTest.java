package com;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Setter
@Getter
public class MinaTest {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * name
     */
    private String name;

    private String age;

    public static void main(String[] args) {
        MinaTest test = new MinaTest();
        System.out.println(JSON.toJSONString(test, SerializerFeature.WriteNullStringAsEmpty));

        HashMap hashMap = new HashMap();
            System.out.println("i--->" + tableSizeFor(17));

    }
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        int a = n >>> 1;
        n |= a;
        int b = n >>> 2;
        n |= b;
        int c = n >>> 4;
        n |= c;
        int d = n >>> 8;
        n |= d;
        int e = n >>> 16;
        n |= e;
        return n + 1;
    }
}
