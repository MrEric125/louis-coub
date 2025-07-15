package com.louis.kafka.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.louis.kafka.KafkaMessageHandler;
import com.louis.kafka.client.canal.CanalFetchHandler;
import com.louis.kafka.client.canal.CanalFetchHandlerContainer;
import com.louis.kafka.common.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author louis
 * @date 2022/8/10
 */
@Slf4j
@Service
public class CanalMessageConsumer implements KafkaMessageHandler<String, String> {
    @Override
    public void onMessage(MessageExt<String, String> messageExt) throws Exception {

        log.info("CanalMessageHandler:topic:{},offset:{},value:{}", messageExt.getTopic(), messageExt.getOffset(), messageExt.getValue());

        if (Objects.isNull(messageExt.getValue())) {
            return;
        }
        String value = messageExt.getValue();

        JSONObject jsonObject = JSON.parseObject(value);

        String table = jsonObject.getString("table");

        CanalFetchHandler fetchHandler = CanalFetchHandlerContainer.getByTableName(table);

        if (Objects.isNull(fetchHandler)) {
            return;
        }

        fetchHandler.handle(value);

    }
}
