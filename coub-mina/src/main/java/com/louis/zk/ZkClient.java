package com.louis.zk;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Service
public class ZkClient {

//    @Value("${localhost:2181,localhost:2102,localhost:2103}")
//    public String registryAddress;

    private ZooKeeper zkClient;

    private String zkServerPath = "localhost:2181";

    private Long sessionId;

    private byte[] sessionPasswd;

    final private CustomerWatch customerWatch = new CustomerWatch();

    @PostConstruct
    public void init() throws IOException {
//        String[] split = registryAddress.split("//");
//        if (2 != split.length) {
//            throw new RuntimeException("zk 注册地址异常");
//        }
//        zkServerPath = split[1];
//        zkServerPath
        return;
//        zkClient = new ZooKeeper(zkServerPath, 5000,customerWatch);
//        sessionId = zkClient.getSessionId();
//        sessionPasswd = zkClient.getSessionPasswd();
    }
    public ZooKeeper getZkClient() {
        return zkClient;
    }
    public CustomerWatch getCustomerWatch() {
        return customerWatch;
    }

}
