package com.louis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
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
public class MinaProject implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static void main(String[] args) {
        SpringApplication.run(MinaProject.class, args);
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("====================");
        log.info("實現自己的启动逻辑：{}", applicationContext.getApplicationName());
        log.info("====================");
    }
}
