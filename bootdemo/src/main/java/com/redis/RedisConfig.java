package com.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author louis
 * <p>
 * Date: 2019/11/8
 * Description:
 */
@Configuration
public class RedisConfig {

    @Bean("redisTemplate")
    public <String,T> RedisTemplate<String,T> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        ObjectMapper om = new ObjectMapper();
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        template.afterPropertiesSet();
        return template;
    }


}
