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


    @Cacheable(key = "#key",value = "test_cache_1_by_Cacheable")
    public List<String> cache1(String key) {
        return Lists.newArrayList("this is test".split(" "));
    }

    @Cacheable(key = "#key",value = "test_cache_2_by_Cacheable")
    public List<Person> cache2(String key) {
        Person person = new Person();
        person.setId(1L);
//        person.setAge(22);
        person.setName("张三");
        return Lists.newArrayList(person);

    }
}
