package com.louis.kafka.canaclient;


/**
 * Kafka 测试基类
 *
 * @author machengyuan @ 2018-6-12
 * @version 1.0.0
 */
public abstract class AbstractKafkaTest extends BaseCanalClientTest {

    public static String topic = "canal_db_topic";
    public static Integer partition = null;
    public static String groupId = "louis";
    public static String servers = "192.168.18.2:9092";
    public static String zkServers = "127.0.0.1:2181";

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}