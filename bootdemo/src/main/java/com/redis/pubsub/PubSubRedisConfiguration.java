package com.redis.pubsub;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author louis
 * <p>
 * Date: 2019/11/25
 * Description:
 */
@Configuration
@EnableCaching
public class PubSubRedisConfiguration  {

    /************** 配置redis发布订阅模式 *******************************/


//    @Bean
//    public MessageListenerAdapter messageListenerAdapter(MessageListener messageListener) {
//        return new MessageListenerAdapter(messageListener);
//    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory,
                                                                       MessageListener messageListenerAdapter) {

//        List<Topic> collection = new ArrayList<>();
//        // 普通订阅，订阅具体的频道
//        ChannelTopic channelTopic = new ChannelTopic("message");
//        collection.add(channelTopic);
//
//        /*// 模式订阅，支持模式匹配订阅，*为模糊匹配符
//        PatternTopic PatternTopic = new PatternTopic("WEB_SOCKET:*");
//        collection.add(PatternTopic);
//        // 匹配所有频道
//        PatternTopic PatternTopicAll = new PatternTopic("*");
//        collection.add(PatternTopicAll);*/
//
//        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
//        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
//        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, collection);
//        return redisMessageListenerContainer;
            RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(factory);
            container.addMessageListener(messageListenerAdapter, new ChannelTopic("testkafka"));
            container.addMessageListener(messageListenerAdapter, new ChannelTopic("testkafka1"));//配置要订阅的订阅项
            return container;

    }
}