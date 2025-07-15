package com.louis.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ZkClientLouis {

    @Value("${registry.address:}")
    public String registryAddress;

    private ZooKeeper zkClient;

    private String zkServerPath;

    private Long sessionId;

    private byte[] sessionPasswd;

    final private CustomerWatch customerWatch = new CustomerWatch();

    @PostConstruct
    public void init() throws IOException {
        String[] split = registryAddress.split("//");

        List<String> collect = Arrays.asList(split).stream().filter(item -> StringUtils.isNotBlank(item)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(collect)) {
            return;
        }
        zkServerPath = split[1];
        if (StringUtils.isBlank(zkServerPath)) {
            return;
        }
        zkClient = new ZooKeeper(zkServerPath, 5000, customerWatch);
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
