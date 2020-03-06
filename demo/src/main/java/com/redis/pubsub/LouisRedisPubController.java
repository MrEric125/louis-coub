package com.redis.pubsub;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/11/25
 * Description:
 */
@RestController
@RequestMapping("/redis")
public class LouisRedisPubController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/publish/{channel}")
    public Wrapper publish(@PathVariable String channel, @RequestParam String message) {
        redisTemplate.convertAndSend(channel, message);
        return WrapMapper.ok();
    }
}
