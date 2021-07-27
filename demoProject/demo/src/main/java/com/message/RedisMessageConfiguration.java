package com.message;

import org.springframework.context.annotation.Configuration;

/**
 * @author jun.liu
 * @since 2021/5/18 17:06
 */
@Configuration
public class RedisMessageConfiguration {

//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory factory, MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer con = new RedisMessageListenerContainer();
//        con.setConnectionFactory(factory);
//        con.addMessageListener(listenerAdapter,new PatternTopic("chat"));
//        return con;
//    }
//
//    @Bean
//    public MessageListenerAdapter adapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
//    @Bean
//    Receiver receiver() {
//        return null;
//
//    }

}
