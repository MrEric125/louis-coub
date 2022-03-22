package com.louis.mybatis.mysql;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.shyiko.mysql.binlog.event.EventType.*;
import static com.louis.mybatis.mysql.BinLogUtils.getdbTable;

/**
 * 数据库监听器
 *
 * @author zrj
 * @since 2021/7/26
 **/
@Slf4j
public class MysqlBinLogEventListener implements BinaryLogClient.EventListener {

    private final BlockingQueue<BinLogItem> queue;

    // 存放每张数据表对应的listener
    private final Multimap<String, BinLogListener> listeners;

    private final Conf conf;
    private final Map<String, Map<String, Column>> dbTableCols;

    Set<EventType> eventList;

    private String dbTable;

    /**
     * 监听器初始化
     *
     *
     */
    public MysqlBinLogEventListener(Conf conf, Set<EventType> eventList,
                                    BlockingQueue<BinLogItem> blockingQueue,
                                    Multimap<String, BinLogListener> listeners) {
        this.conf = conf;
        this.eventList = eventList;
        this.dbTableCols = new ConcurrentHashMap<>();
        // 存放每张数据表对应的listener
        this.listeners = listeners;
        this.queue = blockingQueue;

    }

    /**
     * 监听处理
     *
     * @param event event
     */
    @Override
    public void onEvent(Event event) {
        EventType eventType = event.getHeader().getEventType();
//        String dbTable = null;

        if (eventType == EventType.TABLE_MAP) {
            TableMapEventData tableData = event.getData();
            String db = tableData.getDatabase();
            String table = tableData.getTable();
            dbTable = getdbTable(db, table);
        }

        if (!eventList.contains(eventType)) {
            return;
        }
        log.info("eventType:{},dbTable:{},dbTableCols:{}", eventType, dbTable, dbTableCols);

        // 只处理添加删除更新三种操作
        if (isWrite(eventType)) {
            WriteRowsEventData data = event.getData();
            for (Serializable[] row : data.getRows()) {
                if (dbTableCols.containsKey(dbTable)) {
                    BinLogItem item = BinLogItem.itemFromInsertOrDeleted(row, dbTableCols.get(dbTable), eventType);
                    item.setDbTable(dbTable);
                    queue.add(item);
                }
            }
        }
        if (isUpdate(eventType)) {
            UpdateRowsEventData data = event.getData();
            for (Map.Entry<Serializable[], Serializable[]> row : data.getRows()) {
                if (dbTableCols.containsKey(dbTable)) {
                    BinLogItem item = BinLogItem.itemFromUpdate(row, dbTableCols.get(dbTable), eventType);
                    item.setDbTable(dbTable);
                    queue.add(item);
                }
            }

        }
        if (isDelete(eventType)) {
            DeleteRowsEventData data = event.getData();
            for (Serializable[] row : data.getRows()) {
                if (dbTableCols.containsKey(dbTable)) {
                    BinLogItem item = BinLogItem.itemFromInsertOrDeleted(row, dbTableCols.get(dbTable), eventType);
                    item.setDbTable(dbTable);
                    queue.add(item);
                }
            }
        }
    }

    /**
     * 注册监听
     *
     * @param schema       数据库
     * @param table    操作表
     * @param listener 监听器
     * @throws Exception exception
     */
    public void regListener(String schema, String table, BinLogListener listener) throws Exception {
        String dbTable = BinLogUtils.getdbTable(schema, table);
        // 获取字段集合
        Map<String, Column> cols = BinLogUtils.getColMap(conf, schema, table);
        // 保存字段信息
        dbTableCols.put(dbTable, cols);
        // 保存当前注册的listener
        listeners.put(dbTable, listener);
    }

}
