package com.louis.minaProject.cache;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author jun.liu
 * @date created on 2020/7/29
 * description:
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/cache")
    public HttpResult cache(String key) {

        List<String> list = cacheService.cache1(key);

        List<Person> cache2 = cacheService.cache2(key);

        Map<String, List<?>> map = Maps.newHashMap();
        map.put("cache1", list);
        map.put("cache2", cache2);
        redisTemplate.opsForHash().putAll(  key, map);
        ImmutableMap<String, List<? extends Serializable>> immutableMap = ImmutableMap.of("cache1", list, "cache2", cache2);

        return HttpResult.ok(immutableMap);

    }
    @RequestMapping("/cacheGet")
    public HttpResult cacheGet(String key) {
        Map<Object, Object> entries =redisTemplate.opsForHash().entries(key);
        return HttpResult.ok(entries);
    }

}
