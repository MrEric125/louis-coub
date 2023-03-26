package com.louis.zk;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j(topic = "ZK_WATCH")
public class CustomerWatch implements Watcher {


    @Autowired
    private ZkClientLouis zkClientLouis;
    @Override
    public void process(WatchedEvent event) {
        log.info("接收到watch 信息 {}", event);

        zkClient();
    }

    public void zkClient() {
        ZkClient zkClient = new ZkClient("", 500);

        String path = "/";

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                log.info("节点发生了变化: changed:{}; list:{}", s, JSON.toJSONString(list));
            }
        });
    }


}
