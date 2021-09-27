package com.louis.dubbosourceconsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;


@Slf4j(topic = "ZK_WATCH")
public class CustomerWatch implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        log.info("接收到watch 信息 {}", event);

    }

    public static void main(String[] args) {
        int a = 12345 / 100;
        System.out.println(a);

    }
}
