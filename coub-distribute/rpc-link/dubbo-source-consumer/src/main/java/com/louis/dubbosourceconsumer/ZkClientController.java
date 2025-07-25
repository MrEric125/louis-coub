package com.louis.dubbosourceconsumer;

import com.google.common.collect.Lists;
import com.louis.common.common.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("zkClient")
@RestController
public class ZkClientController {

    @Autowired
    private ZkClient zkClient;


    @GetMapping("/getPath")
    public HttpResult getStat(String path) throws KeeperException, InterruptedException, IOException {


//        log.info("zlClient state:{}", zkClient.getZkClient().getState());
//        if (!zkClient.getState().isAlive()) {
//           zkClient= new ZooKeeper(zkServerPath, 5000, customerWatch, sessionId, sessionPasswd);
//        }

        Stat stat = new Stat();
        byte[] data = zkClient.getZkClient().getData(path, false, stat);
        List<String> children = zkClient.getZkClient().getChildren(path, zkClient.getCustomerWatch());

        Map<String, Object> map = new HashMap<>();
        map.put("data", data == null ? null : new String(data));
        map.put("stat", stat.getCzxid());
        map.put("children", children);
        map.put("aVersion", stat.getAversion());
        map.put("version", stat.getVersion());
        return HttpResult.ok(map);
    }

    @GetMapping("/putData")
    public HttpResult putData(String path, String data) throws KeeperException, InterruptedException {
        Stat exists = zkClient.getZkClient().exists(path, Boolean.TRUE);

        if (exists != null) {
            Stat stat = zkClient.getZkClient().setData(path, data.getBytes(), 0);
            return HttpResult.ok(stat);
        }
        ACL acl = new ACL();
        acl.setPerms(0);
        acl.setId(new Id("zhangSan", "zhangSan"));
        String s = zkClient.getZkClient().create(path, data.getBytes(), Lists.newArrayList(acl), CreateMode.EPHEMERAL);
        return HttpResult.ok(s);

    }

}
