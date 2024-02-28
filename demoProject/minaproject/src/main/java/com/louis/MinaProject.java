package com.louis;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.louis.common.utils.NetUtils;
import com.louis.config.DingTalkConfig;
import com.louis.config.DingTalkSupport;
import com.louis.config.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
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
@Import({DingTalkConfig.class})
public class MinaProject implements ApplicationContextInitializer<ConfigurableApplicationContext>, CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Autowired
    private DingTalkSupport dingTalkSupport;


    public static void main(String[] args) {
        try {
            SpringApplication.run(MinaProject.class, args);
        } catch (Exception e) {
            DingTalkSupport.send("启动失败: " + e.getMessage());
        }
    }

    @Override
    public void initialize(ConfigurableApplicationContext beanFactory) {
        log.info("====================");
        log.info("實現自己的启动逻辑：");
        log.info("====================");
        SpringBeanUtils.setApplicationContext(beanFactory);
    }

    @Override
    public void run(String... args)  {
        String property = configurableEnvironment.getProperty("scrm.sys.available");
        if (StringUtils.isBlank(property) || StringUtils.equals("false", property)) {
            Map<String, Object> systemProperties =
                    configurableEnvironment.getSystemProperties();
            systemProperties.put("scrm.sys.available", "true");
        }
        String ip = NetUtils.getLocalHostIP();
        dingTalkSupport.sendDingTalk("minaproject: 启动成功 ip" + ip);

    }


}
