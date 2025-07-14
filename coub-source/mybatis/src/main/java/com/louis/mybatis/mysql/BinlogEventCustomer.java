package com.louis.mybatis.mysql;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import com.google.common.collect.Multimap;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BinlogEventCustomer {

    private static final Integer consumerThreads = BinLogConstants.consumerThreads;


    private BinaryLogClient binaryLogClient;

    // 存放每张数据表对应的listener
    private Multimap<String, BinLogListener> listeners;

    private BlockingQueue<BinLogItem> queue;

    private final ExecutorService consumer;

    public BinlogEventCustomer(Conf conf, BinaryLogClient.EventListener eventListener,
                               BlockingQueue<BinLogItem> blockingQueue,Multimap<String, BinLogListener> listeners) {
        binaryLogClient = new BinaryLogClient(conf.getHost(), conf.getPort(), conf.getUsername(), conf.getPasswd());
        EventDeserializer eventDeserializer = new EventDeserializer();
        binaryLogClient.setEventDeserializer(eventDeserializer);
        this.listeners = listeners;
        this.consumer = Executors.newFixedThreadPool(consumerThreads);
        this.queue = blockingQueue;
        binaryLogClient.registerEventListener(eventListener);

    }
    /**
     * 开启多线程消费
     *
     * @throws IOException
     */
    public void parse() throws IOException {
//        binaryLogClient.registerEventListener(this);

        for (int i = 0; i < consumerThreads; i++) {
            consumer.submit(() -> {
                while (true) {
                    if (queue.size() > 0) {
                        try {
                            BinLogItem item = queue.take();
                            String dbtable = item.getDbTable();
                            for (BinLogListener binLogListener : listeners.get(dbtable)) {
                                binLogListener.onEvent(item);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(BinLogConstants.queueSleep);
                }
            });
        }
        binaryLogClient.connect();
    }

}
