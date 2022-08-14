package com.louis.kafka.client.canal;

import com.louis.kafka.client.annotation.CanalDataFetchHandle;
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
public class LeadsCARHandler extends AbstractCanalFetchHandler<LeadsChannelAllocRules> {

    @Override
    public void insert(CanalMessage<LeadsChannelAllocRules> leadsChannelAllocRules) {

    }

    @Override
    public void update(CanalMessage<LeadsChannelAllocRules> leadsChannelAllocRules) {

    }

    @Override
    public void delete(CanalMessage<LeadsChannelAllocRules> leadsChannelAllocRules) {

    }
}
