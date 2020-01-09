package com.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 */
public class App {
    public static void main(String[] args) {

        Person person = Person.builder().age(11).name("zhangsan").build();
        Person person1 = Person.builder().age(12).name("lisi").build();
        Person person2 = Person.builder().age(13).name("wangwu").build();
        Person person3 = Person.builder().age(14).name("zhaoliu").build();
        Person person4 = Person.builder().age(15).name("tianqi").build();

        List<Person> list = Lists.newArrayList(person, person1, person2, person3, person4);

        Map<String, Person> stringMap = list.stream().collect(Collectors.toMap(Person::getName, y -> y));
        System.out.println(stringMap);

    }


}
