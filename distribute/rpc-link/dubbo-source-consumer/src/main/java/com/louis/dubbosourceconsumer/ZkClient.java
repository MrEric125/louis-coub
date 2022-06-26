package com.louis.dubbosourceconsumer;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Service
public class ZkClient {

    @Value("${dubbo.registry.address:}")
    public String registryAddress;

    private ZooKeeper zkClient;

    private String zkServerPath;

    private Long sessionId;

    private byte[] sessionPasswd;

    final private CustomerWatch customerWatch = new CustomerWatch();

    @PostConstruct
    public void init() throws IOException {
        String[] split = registryAddress.split("//");
        if (2 != split.length) {
            throw new RuntimeException("zk 注册地址异常");
        }
        zkServerPath = split[1];
        if (StringUtils.isBlank(zkServerPath)) {
            return;
        }
        zkClient = new ZooKeeper(zkServerPath, 5000,customerWatch);
        sessionId = zkClient.getSessionId();
        sessionPasswd = zkClient.getSessionPasswd();
    }
    public ZooKeeper getZkClient() {
        return zkClient;
    }
    public CustomerWatch getCustomerWatch() {
        return customerWatch;
    }

}
