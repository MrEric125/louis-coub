package com.louis.javasource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author louis
 * <p>
 * Date: 2019/7/22
 * Description:
 */
public class SortTest {

    public static void main(String[] args) {
        String[] strings = new String[4];
        strings[0] = "zhangsan";
        strings[1] = "lisi";
        strings[2] = "wangwn";
        strings[3] = "zhaoliu";


        Person[] peoples = new Person[4];

        List<Person> people = new ArrayList<>();

        people.add(new Person("zhangsan", 99));
        people.add(new Person("zhangsan", 11));
        people.add( new Person("lisi", 9));
        people.add(new Person("wangwn", 55));
        people.add(new Person("zhaoliu", 44));

        people.sort(null);

        people = people.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        System.out.println("=================old===============");
        people.forEach(System.out::println);
        System.out.println("=================new===============");
        people.forEach(System.out::println);


    }
}
