package com.louis.kafka.consumer;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Properties;

/**
 * @author John·Louis
 * @date create in 2019/11/17
 * description:
 */
public class OneConsumerTest {

    Properties properties = new Properties();

    public KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);


    public void test1() {
        List<PartitionInfo> partitionInfos;
        List<TopicPartition> topicPartitions = Lists.newArrayList();
        partitionInfos = kafkaConsumer.partitionsFor("topic");
        if (partitionInfos!=null) {
            for (PartitionInfo partitionInfo : partitionInfos) {
                topicPartitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
            }
            kafkaConsumer.assign(topicPartitions);
        }
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic= %s ,partition= %s,offset =%s,customer=%s,country=%s\n",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }
}
