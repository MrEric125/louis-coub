package com.louis.longagocode.concurrent;

import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SyDemo {

    private volatile Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList();

        Iterator<String> iterator = list.iterator();
        if (iterator.hasNext()) {
            String next = iterator.next();
            iterator.remove();
        }
    }


}
