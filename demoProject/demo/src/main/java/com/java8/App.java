package com.java8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 */
public class App extends PubApp {

    public App() {
        System.out.println("bbb");
    }
    static {
        System.out.println("aaaaa");
    }

    public static void main(String[] args) {
        ss();

        Person person = Person.builder().age(11).name("zhangsan").build();
        Person person1 = Person.builder().age(12).name("lisi").build();
        Person person2 = Person.builder().age(13).name("wangwu").build();
        Person person3 = Person.builder().age(14).name("zhaoliu").build();
        Person person4 = Person.builder().age(15).name("tianqi").build();

        List<Person> list = Lists.newArrayList(person, person1, person2, person3, person4);

        Map<String, Person> stringMap = list.stream().collect(Collectors.toMap(Person::getName, y -> y));
        System.out.println(stringMap);

        System.out.println("======");
        ArrayList<Person> arrayList = Lists.newArrayList();
        arrayList.add(person);
        person.setContrary("china");
        person.setName("wangwu");
        arrayList.add(person);
        arrayList.forEach(System.out::println);

        String property = System.getProperty("snn.boot.class.path");
        System.out.println(property);
        String ext = System.getProperty("java.ext.dirs");
        System.out.println(ext);
        System.out.println("================|");
        System.out.println(System.getProperties());
    }


}
