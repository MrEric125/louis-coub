package com.louis.minaProject.cache;

import com.google.common.collect.Lists;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jun.liu
 * @date created on 2020/7/29
 * description:
 */
@Service
public class CacheService {


    @Cacheable(key = "#key",value = "test_cache")
    public List<String> cache1(String key) {
        return Lists.newArrayList("this is test".split(" "));
    }

    @Cacheable(key = "#key",value = "test_cache")
    public List<Person> cache2(String key) {
        Person person = new Person();
        person.setId(1L);
        person.setName("张三");
        return Lists.newArrayList(person);

    }
}
