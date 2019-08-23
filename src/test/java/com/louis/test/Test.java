package com.louis.test;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author louis
 * <p>
 * Date: 2019/8/19
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("a", null);
        String a = map.get("a");
        System.out.println(a);
    }
}
