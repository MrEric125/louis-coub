package com.louis.kafka.client.canal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.louis.kafka.client.annotation.CanalDataFetchHandle;
import com.louis.kafka.client.canal.AbstractCanalFetchHandler;
import com.louis.kafka.client.entity.LeadsChannelAllocRules;
import com.louis.kafka.client.meta.CanalMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author louis
 * @date 2022/8/12
 */
@Slf4j
@Service
@CanalDataFetchHandle(tableName = "leads_channel_alloc_rules")
public class LeadsCARHandler extends AbstractCanalFetchHandler {

    @Override
    public void handle(String msg) {

        CanalMessage<LeadsChannelAllocRules> canalMessage = JSON.parseObject(msg, CanalMessage.class);

        log.info("canalMessage:{}", JSON.toJSONString(canalMessage, SerializerFeature.PrettyFormat));



    }
}
