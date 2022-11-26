package com.louis.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author John·Louis
 * @date create in 2019/11/17
 * description:
 */
@Slf4j
public class OneConsumerTest {


    private static final String ADDR = "localhost:2181";
    // 参数2 zk超时时间
    private static final int TIMEOUT = 5000;
    // 计数器
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        // 创建我们的zk连接
        ZkClient zkClient = new ZkClient(ADDR, TIMEOUT);
        String parentPath = "/";
        // 监听节点发生的变化 监听子节点是否有发生变化 如果发生变化都可以获取到回调通知
        zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                log.info("节点信息发生了变化: s:{}: list:{}", s, JSON.toJSONString(list));
            }
        });

        for (;;){}
        // zkClient.close();
    }


    private ZooKeeper zk = null;
    private String newValue = null;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    private String oldValue = null;

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    /**
     * 启动并连接 Zookeeper
     */
    public ZooKeeper start() throws IOException {
        return new ZooKeeper(ADDR, TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        });
    }

    /**
     * 创建节点
     */
    public void createNode(String path, String data) throws IOException, KeeperException, InterruptedException {
        // 返回当前节点的名称
        String currentNode = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("create zookeeper node success,currentNode******" + currentNode);
    }


    /**
     * 获取当前节点的数据,并且设置观察者
     */
    public String getNodeData(String path) throws IOException, KeeperException, InterruptedException {
        byte[] oldData = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    // 该节点的值是否发生变化的标识,true:发生变化,false:未发生变化
                    Boolean flag = triggerWathcher(path);
                    log.info("*******flag:" + flag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        // 获取当前节点的值,如果当前节点的值发生变化,将新值覆盖原先的值
        if (newValue != null && !oldValue.equals(newValue)) {
            oldValue = newValue;
        }
        oldValue = new String(oldData, "UTF-8");
        return oldValue;
    }

    /**
     * 触发观察者
     */
    public Boolean triggerWathcher(String path) throws IOException, KeeperException, InterruptedException {
        byte[] newData = zk.getData(path, false, new Stat());
        newValue = new String(newData, "UTF-8");

        if (oldValue.equals(newValue)) {
            log.info("******this node value is no change");
            return false;
        } else {
            log.info("******oldValue:" + oldValue + "******" + "newValue:" + newValue);
            return true;
        }
    }
}
