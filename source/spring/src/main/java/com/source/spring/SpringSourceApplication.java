package com.source.spring;

import com.alibaba.fastjson.JSON;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import java.lang.instrument.Instrumentation;

/**
 * @author jun.liu
 * @since 2020/11/14 14:34
 *  {@link SpringBootApplication} 起作用的核心配置是@EnableAutoConfiguration
 *
 * {@link org.springframework.context.annotation.ComponentScan} 只是spring mvc xml功能迁移而已，扫描当前包下的所有带配置类
 * -Xms20m    JVM初始分配的内存20m
 * -Xmx20m   JVM最大可用内存为20m
 * -XX:+HeapDumpOnOutOfMemoryError 当JVM发生OOM时，自动生成DUMP文件
 * -XX:HeapDumpPath=/Users/weihuaxiao/Desktop/dump/  生成DUMP文件的路径
 */
@Slf4j
@EnableTransactionManagement
@MapperScan(basePackages= {"com.source"})
@RestController
@SpringBootApplication
@EnableAdminServer
public class SpringSourceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(SpringSourceApplication.class);
        log.info("msg");
        sa.run();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(JSON.toJSONString(args));
    }

    /**
     * 通过 -javaAgent的方式执行参数.. 通常用于拦截，代理等方式，会在main方法之前注入参数，相当于给jar运行时加参数，例如jrebel的热部署就是通过这个方式加的
     * @param agentOps
     * @param inst
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println(agentOps);
        System.out.println(agentOps);

    }



}
