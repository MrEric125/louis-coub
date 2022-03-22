package com.louis.mybatis.mysql;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.github.shyiko.mysql.binlog.event.EventType.isDelete;
import static com.github.shyiko.mysql.binlog.event.EventType.isUpdate;
import static com.github.shyiko.mysql.binlog.event.EventType.isWrite;

/**
 * 监听工具
 *
 * @author zrj
 * @since 2021/7/27
 **/
@Slf4j
@Component
public class BinLogUtils {

    /**
     * 拼接dbTable
     */
    public static String getdbTable(String db, String table) {
        return db + "." + table;
    }

    /**
     * 获取columns集合
     */
    public static Map<String, Column> getColMap(Conf conf, String db, String table) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 保存当前注册的表的colum信息
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + conf.getHost() + ":" + conf.getPort(), conf.getUsername(), conf.getPasswd());
            // 执行sql
            String preSql = "SELECT TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, DATA_TYPE, ORDINAL_POSITION FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? and TABLE_NAME = ?";
            PreparedStatement ps = connection.prepareStatement(preSql);
            ps.setString(1, db);
            ps.setString(2, table);
            ResultSet rs = ps.executeQuery();
            Map<String, Column> map = new LinkedHashMap<>(rs.getRow());
            while (rs.next()) {
                String schema = rs.getString("TABLE_SCHEMA");
                String tableName = rs.getString("TABLE_NAME");
                String column = rs.getString("COLUMN_NAME");
                int idx = rs.getInt("ORDINAL_POSITION");
                String dataType = rs.getString("DATA_TYPE");
                if (column != null && idx >= 1) {
                    map.put(column, new Column(schema, tableName, idx - 1, column, dataType)); // sql的位置从1开始
                }
            }
            ps.close();
            rs.close();
            return map;
        } catch (SQLException e) {
            log.error("load db conf error, db_table={}:{} ", db, table, e);
        }
        return null;
    }
    /**
     * 将逗号拼接字符串转List
     * @param str
     * @return
     */
    public static List<String> getListByStr(String str) {
        if (StringUtils.isEmpty(str)) {
            return Lists.newArrayList();
        }
        return Arrays.asList(str.split(","));
    }
}

