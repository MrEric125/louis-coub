package com.louis.longagocode.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisLockService {

    private static final String RELEASE_SUCCESS = "success";

    private static final Long DEFAULT_TIME = 3000L;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public boolean tryLock(String lockKey, String value) {
        return tryLock(lockKey, value, DEFAULT_TIME, TimeUnit.MILLISECONDS);
    }

    public Boolean tryLock(String lockKey, String value, Long expire, TimeUnit timeUnit) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(lockKey, value, expire, timeUnit);
        } catch (Exception e) {
            log.error("加锁报错：{}", lockKey, e);
            return false;
        }

    }

    public Boolean releaseLock(String lockKey,String value) {

        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                    "then return redis.call('del', KEYS[1]) " +
                    "else return 0 " +
                    "end";
            DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            redisScript.setResultType(String.class);
            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);

            if (RELEASE_SUCCESS.equals(result.toString())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("释放锁（" + lockKey + "）发生异常：{}",e);
            return false;
        }
    }


}
