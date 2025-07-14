//package com.louis.mybatis.mysql;
//
//
//
//import com.github.shyiko.mysql.binlog.BinaryLogClient;
//import com.github.shyiko.mysql.binlog.event.*;
//import lombok.extern.slf4j.Slf4j;
//
//
///**
// * 这里可能会有客户端鉴权不兼容的问题
// * ALTER USER root IDENTIFIED WITH mysql_native_password BY 'Root'
// * https://www.jianshu.com/p/a9dbd3fd52f3 一些事件的文档
// */
//@Slf4j
//public class BinLogEvent {
//
//    public static void main(String[] args) {
//        BinaryLogClient client = new BinaryLogClient("localhost", 3306, "louis-coub","root", "Root");
//
//        client.registerEventListener(event -> {
//            EventData data = event.getData();
//            if (data instanceof TableMapEventData) {
//                log.info("Table:");
//                TableMapEventData tableMapEventData = (TableMapEventData) data;
//                log.info(tableMapEventData.getTableId()+": ["+tableMapEventData.getDatabase() + "-" + tableMapEventData.getTable()+"]");
//            }
//            if (data instanceof UpdateRowsEventData) {
//                log.info("Update:");
//                log.info(data.toString());
//            } else if (data instanceof WriteRowsEventData) {
//                log.info("Insert:");
//                log.info(data.toString());
//            } else if (data instanceof DeleteRowsEventData) {
//                log.info("Delete:");
//                log.info(data.toString());
//            } else if (data instanceof QueryEventData) {
//                log.info("query data:{}", data.toString());
//            }
//        });
//
//        try {
//            client.connect(500L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
