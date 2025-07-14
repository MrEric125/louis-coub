package com.mock;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import static java.util.Comparator.comparingInt;

@Component
public class OrderHelper {


    public String resolve() throws Exception {
        return "resolve order";
    }


    public static void main(String[] args) {
        // Anonymous class instance as a function object - obsolete!
        ArrayList<@Nullable String> words = Lists.newArrayList("apple", "banana", "kiwi", "grape", "orange");
        Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        // Lambda expression as function object (replaces anonymous class)
        Collections.sort(words,
                (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        Collections.sort(words, comparingInt(String::length));

        words.sort(comparingInt(String::length));

        Map<String, Integer> map = Maps.newHashMap();

        String key = "1";
//        如果没有给定键的映射，则该方法只是插入给定值; 如果映射已经存在，则合并给定函数应用于当前值和给定值，并用结果覆盖当前值。
        map.merge(key, 1, (count, incr) -> count + incr);


    }

}
