package com.pattern.jpa;


import org.assertj.core.util.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * @author jun.liu
 * @date created on 2020/7/18
 * description:
 */
public class BaseService implements IBaseService<String, BizService> {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override

    public String queryById(String id) {
        System.out.println(id);
        return id + "this is id";
    }

    @Override
    public void cacheByMethod(String key, String methodName, Class<?>... paramTypes) {

        Method method = BeanUtils.findMethod(BizService.class, methodName, paramTypes);


        String value = "";

//        method.invoke()

        redisTemplate.opsForValue().set(key, value);


    }

    public Set<String> scan(String pattern) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(Integer.MAX_VALUE).build();
            Cursor<byte[]> scan = connection.scan(scanOptions);
            Set<String> sets = Sets.newHashSet();
            if (scan.hasNext()) {
                byte[] next = scan.next();
                String string = Arrays.toString(next);
                sets.add(string);

            }
            return sets;
        });
    }
}
