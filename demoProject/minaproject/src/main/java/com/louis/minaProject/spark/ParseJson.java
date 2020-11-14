package com.louis.minaProject.spark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.avro.ipc.specific.Person;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author jun.liu
 * @date created on 2020/7/26
 * description:
 */
public class ParseJson implements FlatMapFunction<Iterator<String>, Person> {
    @Override
    public Iterator<Person> call(Iterator<String> stringIterator) throws Exception {
        ArrayList<Person> list = Lists.newArrayList();
        ObjectMapper mapper = new ObjectMapper();
        while (stringIterator.hasNext()) {
            String next = stringIterator.next();
            try {
                list.add(mapper.readValue(next, Person.class));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return list.iterator();
    }

}
