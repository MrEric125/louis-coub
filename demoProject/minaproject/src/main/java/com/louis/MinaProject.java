package com.louis;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.louis.common.utils.NetUtils;
import io.netty.util.NetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
@EnableJpaRepositories
@EnableScheduling
@Slf4j
@EnableTransactionManagement
@MapperScan(basePackages= {"com.louis"},sqlSessionFactoryRef="sqlSessionFactory")
@SpringBootApplication
@RestController
public class MinaProject implements ApplicationContextInitializer<ConfigurableApplicationContext>, CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Autowired
    private DingTalkClient dingTalkClient;

    public static void main(String[] args) {
        SpringApplication.run(MinaProject.class, args);
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("====================");
        log.info("實現自己的启动逻辑：{}", applicationContext.getApplicationName());
        log.info("====================");
    }

    @Override
    public void run(String... args) throws Exception {
        String property = configurableEnvironment.getProperty("scrm.sys.available");
        if (StringUtils.isBlank(property) || StringUtils.equals("false", property)) {
            Map<String, Object> systemProperties =
                    configurableEnvironment.getSystemProperties();
            systemProperties.put("scrm.sys.available", "true");
        }
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        request.setTimestamp(System.currentTimeMillis());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        String ip = NetUtils.getLocalHostIP();
        text.setContent("启动成功 ip: " + ip);
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
        request.setAt(at);
        OapiRobotSendResponse response = dingTalkClient.execute(request);
        log.info(response.getBody());

    }
}
