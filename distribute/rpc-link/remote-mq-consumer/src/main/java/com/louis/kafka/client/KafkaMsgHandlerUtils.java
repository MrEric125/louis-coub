package com.louis.kafka.client;

import com.google.common.collect.Maps;
import com.louis.kafka.KafkaMessageHandler;

import java.util.Map;

/**
 * @author louis
 * @date 2022/9/7
 */
public class KafkaMsgHandlerUtils {

    private static Map<String, KafkaMessageHandler> msgHandlerMap = Maps.newConcurrentMap();

    public static void putHandler(String topicTemp,KafkaMessageHandler msgHandler) {
        msgHandlerMap.put(topicTemp, msgHandler);
    }

    public static KafkaMessageHandler getHandler(String topicTemp) {
       return msgHandlerMap.get(topicTemp);
    }
}
