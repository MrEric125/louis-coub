package com.louis.kafka.client.canal;

import com.louis.kafka.client.meta.CanalMessage;

import java.io.Serializable;

/**
 * @author louis
 * @date 2022/8/12
 */
public interface CanalFetchHandler<T extends Serializable> {

    void handle(String msg);

    void insert(CanalMessage<T> t);

    void update(CanalMessage<T> t);

    void delete(CanalMessage<T> t);
}
