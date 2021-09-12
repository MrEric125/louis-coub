package com.louis.dubbosourceconsumer;

import com.google.common.collect.Lists;
import com.louis.common.common.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("zkClient")
@RestController
public class ZkClientController {

    @Value("${dubbo.registry.address}")
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
        zkClient = new ZooKeeper(zkServerPath, 5000,customerWatch);
        sessionId = zkClient.getSessionId();
        sessionPasswd = zkClient.getSessionPasswd();
    }


    @GetMapping("/getPath")
    public HttpResult getStat(String path) throws KeeperException, InterruptedException, IOException {


        log.info("zlClient state:{}", zkClient.getState());
        if (!zkClient.getState().isAlive()) {
           zkClient= new ZooKeeper(zkServerPath, 5000, customerWatch, sessionId, sessionPasswd);
        }

        Stat stat = new Stat();
        byte[] data = zkClient.getData(path, false, stat);
        List<String> children = zkClient.getChildren(path, customerWatch);

        Map<String, Object> map = new HashMap<>();
        map.put("data", data == null ? null : new String(data));
        map.put("stat", stat.getCzxid());
        map.put("children", children);
        map.put("aVersion", stat.getAversion());
        map.put("version", stat.getVersion());
        return HttpResult.ok(map);
    }

    @GetMapping("/putData")
    public HttpResult putData(String path,String data) throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists(path, Boolean.TRUE);

        if (exists != null) {
            Stat stat = zkClient.setData(path, data.getBytes(), 0);
            return HttpResult.ok(stat);
        }
        ACL acl = new ACL();
        acl.setPerms(0);
        acl.setId(new Id("zhangsan","zhangsan"));
        String s = zkClient.create(path, data.getBytes(), Lists.newArrayList(acl), CreateMode.EPHEMERAL);
        return HttpResult.ok(s);

    }

}
