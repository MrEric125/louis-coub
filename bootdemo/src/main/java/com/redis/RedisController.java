package com.redis;

import com.alibaba.fastjson.JSONObject;
import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/11/8
 * Description:
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;



    @RequestMapping("/get")
    public Wrapper getRedis(@RequestParam String redisParam) {
        Object object = redisTemplate.opsForValue().get(redisParam);
//        HashMap hashMap = new HashMap();
//        object = hashMap.getName();
        return WrapMapper.ok(JSONObject.toJSON(object));
    }
}
