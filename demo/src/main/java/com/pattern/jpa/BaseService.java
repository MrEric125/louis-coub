package com.pattern.jpa;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

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
}
