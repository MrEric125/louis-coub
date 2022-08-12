package com.louis.kafka.client.canal;

import com.louis.kafka.client.annotation.CanalDataFetchHandle;
import org.springframework.beans.factory.InitializingBean;

import java.util.Objects;

/**
 * @author louis
 * @date 2022/8/12
 */
public abstract class AbstractCanalFetchHandler implements CanalFetchHandler, InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

        Class<? extends AbstractCanalFetchHandler> aClass = this.getClass();

        CanalDataFetchHandle annotation = aClass.getAnnotation(CanalDataFetchHandle.class);

        if (Objects.nonNull(annotation)) {
            String tableName = annotation.tableName();
            CanalFetchHandlerContainer.putByTableName(tableName, this);
        }

    }
}
