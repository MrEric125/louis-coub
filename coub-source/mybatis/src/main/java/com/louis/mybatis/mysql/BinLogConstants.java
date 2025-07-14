package com.louis.mybatis.mysql;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 监听配置信息
 *
 * @author zrj
 * @since 2021/7/27
 **/
@Data
@Component
public class BinLogConstants {
    @Value("${binlog.datasource.host}")
    private String host;

    @Value("${binlog.datasource.port}")
    private int port;

    @Value("${binlog.datasource.username}")
    private String username;

    @Value("${binlog.datasource.password}")
    private String password;

    @Value("${binlog.schema}")
    private String schema;

    @Value("${binlog.table}")
    private String table;

    public static final int consumerThreads = 5;

    public static final long queueSleep = 1000;

    public static final Set<EventType> writeType = Sets.newHashSet(EventType.PRE_GA_WRITE_ROWS, EventType.WRITE_ROWS, EventType.EXT_WRITE_ROWS);
    public static final Set<EventType> updateType = Sets.newHashSet(EventType.PRE_GA_UPDATE_ROWS, EventType.UPDATE_ROWS, EventType.EXT_UPDATE_ROWS);
    public static final Set<EventType> deleteType = Sets.newHashSet(EventType.PRE_GA_DELETE_ROWS, EventType.DELETE_ROWS, EventType.EXT_DELETE_ROWS);

}

