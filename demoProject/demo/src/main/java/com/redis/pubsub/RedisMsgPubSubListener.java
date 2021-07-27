package com.redis.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author louis
 * <p>
 * Date: 2019/11/25
 * Description:
 */
@Slf4j
@Service
public class RedisMsgPubSubListener implements MessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        RedisSerializer<?> serializer = redisTemplate.getValueSerializer();
        // message.getBody()是Redis的值，需要用redis的valueSerializer反序列化
        log.info("Message receive--> pattern:{}:message body:{}，message channel: {}", new String(pattern),
                serializer.deserialize(message.getBody()),
                stringSerializer.deserialize(message.getChannel()));

        //可以与WebSocket结合使用，解决分布式服务中，共享Session
    }
}
