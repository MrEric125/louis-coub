package com.louis.kafka.client.canal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.louis.kafka.client.annotation.CanalDataFetchHandle;
import com.louis.kafka.client.meta.CanalMessage;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author louis
 * @date 2022/8/12
 */
public abstract class AbstractCanalFetchHandler<T extends Serializable> implements CanalFetchHandler<T>, InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

        Class<?> aClass = this.getClass();

        CanalDataFetchHandle annotation = aClass.getAnnotation(CanalDataFetchHandle.class);

        if (Objects.nonNull(annotation)) {
            String tableName = annotation.tableName();
            CanalFetchHandlerContainer.putByTableName(tableName, this);
        }

    }

    @Override
    public void handle(String msg) {

        CanalMessage<T> canalMessage = JSON.parseObject(msg, new TypeReference<CanalMessage<T>>() {
        });

        CanalEntry.EventType type = canalMessage.getType();

        switch (type){
            case DELETE:
                delete(canalMessage);
            case INSERT:
                insert(canalMessage);
            case UPDATE:
                update(canalMessage);
        }

    }
}
