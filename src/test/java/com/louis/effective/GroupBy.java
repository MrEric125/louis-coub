package com.louis.effective;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/7/26
 * Description:
 */
public class GroupBy {

    public static void main(String[] args) {
        Person person1 = new Person(1, "zhangsan", 33, 88, "similar", "good man");
        Person person2 = new Person(1, "lisi", 44, 88, "high", "good man");
        Person person3 = new Person(2, "wangwu", 55, 80, "similar", "good man");
        Person person4 = new Person(2, "zhaoliu", 44, 80, "high", "bad man");
        Person person5 = new Person(3, "geqi", 66, 88, "similar", "good man");
        Person person6 = new Person(4, "fangba", 22, 80, "low", "good man");

        List<Person> list = Lists.newArrayList(person1, person2, person3, person4, person5, person6);
        Map<Long, Map<Integer, Map<String, List<Person>>>> collect = list.stream().collect(Collectors.groupingBy(Person::getId, Collectors.groupingBy(Person::getScore, Collectors.groupingBy(Person::getDescription))));
        System.out.println(collect);

    }
}
