package com.collection;

import java.util.HashMap;

/**
 * @author louis
 * <p>
 * Date: 2019/8/27
 * Description:
 */
public class HashMapTest {

    public static void main(String[] args) {

        String key = "zhangsan";
        int h = key.hashCode();
        int j = h >>> 16;
        int i =  h ^ (h >>> 16);
        System.out.println("h===> " + h + " i===>" + i+"j==>"+j);
    }
}
