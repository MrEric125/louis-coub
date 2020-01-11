package com.java8;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/12/23
 * Description:
 */
@Slf4j
public class StaticsTest {

    public static void main(String[] args) throws Exception {

        Person person = new Person();
        person.setName("zhangsan");
        person.setContrary("");
        person.setType("");

        Person format = format(person,Lists.<String>newArrayList("serialVersionUID"),"",null);
        System.out.println(format);


    }

    private static void staticstic() {
        Person person1 = Person.builder().age(22).weight(140).build();
        Person person2 = Person.builder().age(33).weight(150).build();
        List<Person> personList = Lists.newArrayList(person1, person2);

        long sum = personList.stream().mapToInt(Person::getWeight).summaryStatistics().getSum();
        double average = personList.stream().mapToInt(Person::getAge).average().orElse(0);

        System.out.println(sum);
        System.out.println(average);
    }

    private static <T> T format(T obj,List<String> excludeFields,String initialValue,String formatValue) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName=field.getName();
            if (excludeFields.contains(fieldName)) {
                continue;
            }
            try {
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
                Method getMethod = pd.getReadMethod();
                Object invoke = getMethod.invoke(obj);

                if (invoke instanceof String) {
                    String fieldValue = (String) invoke;
                    if (fieldValue.equals(initialValue)) {
                        field.setAccessible(true);
                        field.set(obj,formatValue);
                    }
                }

            } catch (Exception e) {
                log.warn("format exception:{}",e.getMessage(),e);
            }

        }
        return obj;
    }
}
