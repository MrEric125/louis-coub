package com.louis.kafka.client.meta;

import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author louis
 * @date 2022/8/11
 */
@Data
public class CanalMessage<T> implements Serializable {


    private static final long serialVersionUID = -3377801448502122221L;

    private List<T> data;

    private String database;

    private Long es;

    private Long id;

    private Boolean isDdl;

    /**
     * 数字字段类型
     */
    private Map<String,String> mysqlType;

    private List<T> old;

    private List<String> pkNames;

    private String sql;

    private Map<String,Integer>  sqlType;

    private String table;

    private Long ts;

    private CanalEntry.EventType type;


}
