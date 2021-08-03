package com.source.spring.ioc;

import com.google.common.collect.Maps;
import com.source.spring.ioc.domain.DomainHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * IOC 容器初始化逻辑
 * ======
 * 1. 如果是register,那么直接就往容器中放就可以
 * 2. 如果是scan ,
 *      1. 拿到所有的类，
 *      2. 这个类上是否有可以被我们管理的注解，比如@Component @Service等
 *      3. 挑出刚才加载到的bean,开始注入到容器中
 *      4. 实例化需要实例化的bean
 */
public class IOCApp {

    private Map<String, Object> maps = Maps.newConcurrentMap();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DomainHolder.class);
        context.scan("com.source.spring.ioc");
        context.refresh();
        DomainHolder bean = context.getBean(DomainHolder.class);
        System.out.println(bean.getName());



    }
}
