package com.louis.kafka.client.canal;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * @date 2022/8/12
 */
public class CanalFetchHandlerContainer {

    private static Map<String, CanalFetchHandler> canalFetchHandlerMap = new ConcurrentHashMap<>(32);

    public static CanalFetchHandler getByTableName(String tableName) {
        return canalFetchHandlerMap.get(tableName);
    }

    public static void putByTableName(String tableName, CanalFetchHandler canalFetchHandler) {
        canalFetchHandlerMap.put(tableName, canalFetchHandler);

    }


}
