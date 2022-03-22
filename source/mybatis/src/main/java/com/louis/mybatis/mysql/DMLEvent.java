package com.louis.mybatis.mysql;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DMLEvent implements BinLogListener{


    @Override
    public void onEvent(BinLogItem item) {

        if (BinLogConstants.writeType.contains(item.getEventType())) {
        }

    }
}
