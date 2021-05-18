package com.source.spring;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.louis.common.common.HttpResult;
import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author jun.liu
 * @since 2020/11/14 14:34
 * @SpirngbootApplication起作用的核心配置是@EnableAutoConfiguration
 *
 * @ComponentScan只是spring mvc xml功能迁移而已，扫描当前包下的所有带配置类
 */
@RestController
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
//@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
//        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public class SpringSourceApplication implements CommandLineRunner, ApplicationContextAware {

    Cache<String, String> myCache = CacheBuilder.newBuilder().concurrencyLevel(4).expireAfterWrite(10, TimeUnit.DAYS).maximumSize(10000).build();

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(SpringSourceApplication.class);
        sa.setBannerMode(Banner.Mode.OFF);
        sa.run();
//        run.publishEvent(new MyEvent(new Object()));


    }

    @RequestMapping("/env")
    public HttpResult env(@RequestBody() String data)  {
        return HttpResult.ok(data);
    }
    @RequestMapping("/add")
    public HttpResult add(String data,String data2) {
        return HttpResult.ok(data);
    }

    //todo 在SpringApplication.run 运行完成之前执行，特殊逻辑,实现原理是什么样子的？？？
    @Override
    public void run(String... args) throws Exception {
        System.out.println(args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
