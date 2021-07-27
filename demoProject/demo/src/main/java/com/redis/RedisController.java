package com.redis;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    RedisTemplate<String,String> redisTemplate;




    @RequestMapping("/get")
    public HttpResult getRedis(@RequestParam String redisParam) {
        Set<Object> keys = redisTemplate.boundHashOps(redisParam).keys();
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();


        DataType type = redisTemplate.type(redisParam);
        Map<String, String> returnMap = Maps.newHashMap();
        returnMap.put("dataType", type != null ? type.code() : null);
        returnMap.put("key", JSONObject.toJSONString(keys));
        return HttpResult.ok(returnMap);

    }

    @RequestMapping("/set")
    public HttpResult setRedis(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return HttpResult.ok();
    }

    @RequestMapping("/getClient")
    public HttpResult getClient() {

        List<RedisClientInfo> clientList = redisTemplate.getClientList();

        return HttpResult.ok(clientList);

    }
    @RequestMapping("/zSetOperate")
    public HttpResult zSetOperate() {
//        redisTemplate.opsForZSet().range()
        redisTemplate.opsForZSet().range("mySet", 0, -1);

        return HttpResult.ok();

    }
}
