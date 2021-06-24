package com.louis;

import com.google.common.collect.Lists;
import com.louis.common.common.HttpResult;
import com.louis.lessifelse.OrderService;
import com.louis.minashop.i18n.LocaleMessageSourceService;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.DefaultServerWebExchange;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
@MapperScan(basePackages= {"com.louis"},sqlSessionFactoryRef="sqlSessionFactory")
@SpringBootApplication
@RestController
public class MinaProject implements ExitCodeGenerator, ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[] args) {
        SpringApplication.run(MinaProject.class, args);

//        System.exit(SpringApplication.exit(SpringApplication.run(MinaProject.class, args)));
    }

    @LocalServerPort
    private String port;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public int getExitCode() {

        return 01111;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        System.out.println("=======China");

        System.out.println(port);
        InetAddress address = InetAddress.getLocalHost();
        if (address != null) {
            String hostName = address.getHostName();
            System.out.println(hostName);
            System.out.println(address.getHostAddress());
        }
    }


    @Bean
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(14);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(1);
        return executor;
    }

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 测试线程池报错
     * @return
     */
    @RequestMapping("/threadPool")
    public HttpResult threadPool() {
        for (int i = 0; i < 10; i++) {
            doTasks();
            System.out.println("end=====");

        }

        return HttpResult.ok();
    }
    private void doTasks() {
        ArrayList<Future> objectArrayList = Lists.newArrayListWithCapacity(15);
        for(int i = 0; i < 15; ++i){
            Future submit = taskExecutor.submit(() -> {
                int sec = new Double(Math.random() * 5).intValue();
                LockSupport.parkNanos(sec * 1000 * 1000 * 1000);
                System.out.println(Thread.currentThread().getName() + " end");
            });
            objectArrayList.add(submit);

        }

        for(Future future : objectArrayList){
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
