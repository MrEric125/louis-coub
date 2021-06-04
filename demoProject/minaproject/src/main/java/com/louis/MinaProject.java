package com.louis;

import com.louis.lessifelse.OrderService;
import com.louis.minashop.i18n.LocaleMessageSourceService;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
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
public class MinaProject implements ExitCodeGenerator, ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[] args) {
        SpringApplication.run(MinaProject.class, args);

//        System.exit(SpringApplication.exit(SpringApplication.run(MinaProject.class, args)));
    }

    @LocalServerPort
    private String port;


    @Override
    public int getExitCode() {
        return 01111;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();

        MyBean sourceService = applicationContext.getBean(MyBean.class);

        sourceService.run("China");

        System.out.println("=======China");

        System.out.println(port);
        InetAddress address = InetAddress.getLocalHost();
        if (address != null) {
            String hostName = address.getHostName();
            System.out.println(hostName);
            System.out.println(address.getHostAddress());
        }
    }
}
