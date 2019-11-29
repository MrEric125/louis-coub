//package com.redis.pubsub;
//
//import com.alibaba.fastjson.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.stereotype.Service;
//
///**
// * @author louis
// * <p>
// * Date: 2019/11/25
// * Description:
// */
//@Service
//public class CustomRedisMsgPubSubListener implements MessageListener {
//    private Logger logger = LoggerFactory.getLogger(CustomRedisMsgPubSubListener.class);
//
//    @Autowired
//    private ApplicationContext applicationContext;
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    /**
//     * 实例：
//     *    JSONObject json = new JSONObject();
//     *    json.put("cutomerId", notifyResult.getResult());
//     *    json.put("resultCode", map.get("resultCode"));
//     *    //向redis发布消息
//     *    redisTemplate.convertAndSend(channelName, json);
//     * @param message
//     * @param pattern
//     * @Throws
//     * @Author: chetao
//     * @Date: 2019年1月8日 下午10:40:21
//     * @see org.springframework.data.redis.connection.MessageListener#onMessage(org.springframework.data.redis.connection.Message, byte[])
//     */
//    @Override
//    public void onMessage(final Message message, final byte[] pattern ) {
//        RedisSerializer<?> serializer = redisTemplate.getKeySerializer();
//        logger.info("Message receive-->pattern:{}，message: {},{}", serializer.deserialize(pattern),
//                serializer.deserialize(message.getBody()), serializer.deserialize(message.getChannel()));
//        if ("WEB_SOCKET:PAY_NOTIFY".equals(serializer.deserialize(message.getChannel()))) {
//            RedisMessageListenerContainer redisMessageListenerContainer = applicationContext
//                    .getBean("redisMessageListenerContainer", RedisMessageListenerContainer.class);
//            MessageListenerAdapter messageListenerAdapter = applicationContext.getBean("messageListenerAdapter",
//                    MessageListenerAdapter.class);
//            /*List<Topic> collection = new ArrayList<Topic>();
//            // 动态添加订阅主题
//            ChannelTopic channelTopic = new ChannelTopic("WEB_SOCKET1:PAY_NOTIFY");
//            collection.add(channelTopic);
//            PatternTopic PatternTopic = new PatternTopic("WEB_SOCKET:*");
//            collection.add(PatternTopic);
//            redisMessageListenerContainer.addMessageListener(messageListenerAdapter, collection);*/
//            // 动态添加订阅主题
//            ChannelTopic channelTopic = new ChannelTopic("WEB_SOCKET1:PAY_NOTIFY");
//            redisMessageListenerContainer.addMessageListener(messageListenerAdapter, channelTopic);
//            PatternTopic PatternTopic = new PatternTopic("WEB_SOCKET:*");
//            redisMessageListenerContainer.addMessageListener(messageListenerAdapter, PatternTopic);
//        }
//
//        JSONObject json = JSONObject.parseObject(message.toString());
//    }
//}
