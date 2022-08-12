package com.louis.kafka.client.canal;

/**
 * @author louis
 * @date 2022/8/12
 */
public interface CanalFetchHandler {


     void handle(String msg);
}
