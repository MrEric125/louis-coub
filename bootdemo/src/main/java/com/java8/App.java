package com.java8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 */
public class App {
    public static void main(String[] args) {

        List<String> list = Lists.newArrayList();
        list.add("zh");
        list.add("qian");
        list.add("sun");
        list.add("li");
        list.add("zhou");
        List<String> li = list.stream().filter(x -> x.equals("li")).collect(Collectors.toList());
        System.out.println("list++++++++++++++++");
        list.forEach(System.out::println);
        System.out.println("li================");
        li.forEach(System.out::println);

    }


}
