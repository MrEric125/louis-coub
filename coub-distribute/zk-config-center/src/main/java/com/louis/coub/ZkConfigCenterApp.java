package com.louis.coub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.zookeeper.config.ZookeeperConfigProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
@RestController
@Slf4j
public class ZkConfigCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(ZkConfigCenterApp.class);
    }

    //    @Autowired
    private ZookeeperConfigProperties zookeeperConfigProperties;


    @Value("${foo:hello}")
    private String value;

    @RequestMapping("config")
    public String configValue() {
        log.info("=={}==", value);
        log.info("properties:{}", zookeeperConfigProperties);
        return value;

    }


}
