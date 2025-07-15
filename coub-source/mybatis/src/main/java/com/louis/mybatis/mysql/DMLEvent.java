package com.louis.mybatis.mysql;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DMLEvent implements BinLogListener {


    @Override
    public void onEvent(BinLogItem item) {
//
//        if (BinLogConstants.writeType.contains(item.getEventType())) {
//
//        }

        log.info("binlog item :{}", JSON.toJSONString(item));

    }
}
