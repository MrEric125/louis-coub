/**
 * kuaike.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

/**
 * @author yanmaoyuan
 * @version 1.0
 * @title KafkaAdminServiceImpl
 * @date 2019年7月25日
 */
@Service
@Slf4j
public class KafkaAdminServiceImpl {

    @Autowired
    private AdminClient adminClient;

    public Set<String> listTopics() {
        TreeSet<String> names = new TreeSet<String>();

        ListTopicsResult list = adminClient.listTopics();
        KafkaFuture<Set<String>> future = list.names();
        try {
            while (!future.isDone()) {
                Thread.sleep(100L);// 等待100ms
            }
            names.addAll(future.get());// 列出topic
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return names;
    }

    /**
     * 判断topic是否存在
     *
     * @param topic
     */
    public boolean isExist(String topic) {
        DescribeTopicsResult result = adminClient.describeTopics(Collections.singleton(topic));

        KafkaFuture<Map<String, TopicDescription>> future = result.all();

        try {


            while (!future.isDone()) {
                Thread.sleep(100L);// 等待100ms
            }
            // 当topic 不存在时，此行会抛出异常，由此可知topic不存在。
            // java.util.concurrent.ExecutionException: org.apache.kafka.common.errors.UnknownTopicOrPartitionException: This server does not host this topic-partition.
            future.get();

            // 若存在，则可以获取到topic的描述信息，但这个方法内并不需要它。
            // Map<String, TopicDescription> map = future.get();
            // TopicDescription desc = map.get(topic);

            return true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 创建topic
     *
     * @param topic
     * @return
     */
    public boolean createTopic(String topic, int numPartitions, short replicationFactor) {
        log.info("create topic with name={}, numPartitions={}, replicationFactor={}", topic, numPartitions, replicationFactor);
        NewTopic newTopic = new NewTopic(topic, numPartitions, replicationFactor);
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(newTopic));

        try {
            Map<String, KafkaFuture<Void>> map = createTopicsResult.values();
            KafkaFuture<Void> future = map.get(topic);
            while (!future.isDone()) {
                Thread.sleep(100L);// 等待100ms
            }


            // 当topic存在时，调用此方法会抛出异常。
            // java.util.concurrent.ExecutionException: org.apache.kafka.common.errors.TopicExistsException: Topic 'wx_event_wxc7550253cd3cffe2_dev' already exists.
            future.get();

            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }
}
