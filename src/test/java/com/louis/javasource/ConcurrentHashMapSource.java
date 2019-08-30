package com.louis.javasource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * <p>
 * Date: 2019/7/15
 * Description:
 */
public class ConcurrentHashMapSource {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("aa", "ab");
        for (Map.Entry<String, String> stringStringEntry : concurrentHashMap.entrySet()) {

        }
        int i = 1 >> 30;
        System.out.println(i);
    }
}
