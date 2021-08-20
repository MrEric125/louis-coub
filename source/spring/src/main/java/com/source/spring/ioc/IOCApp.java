package com.source.spring.ioc;

import com.google.common.collect.Maps;
import com.source.spring.ioc.domain.DomainHolder;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.support.RootBeanDefinition;
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
 *      ConfigurationClassBeanDefinition 中factoryMethodName 功能是什么
 *      循环依赖成功情况
 *      1.成功
 *          1. setter
 *          @Autowire
 *          @Resource
 *          在@Configuration中@Bean中不手动set属性，而是在方法上加上@Resource或者@Autowire
 *      2. 失败
 *          1. 构造函数
 *          2. @Configuration中的 @Bean 中手动set属性
 *
 *
 * 解决循环依赖的三级依赖分别为：
 * 1. {@link org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#singletonObjects}
 * 2. {@link org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#singletonFactories}
 * 3. {@link org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#earlySingletonObjects}
 *
 * 属性注入逻辑在这里操作
 * {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)}
 *
 */
public class IOCApp {

    private Map<String, Object> maps = Maps.newConcurrentMap();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(DomainHolder.class);
        context.scan("com.source.spring.ioc");
        context.refresh();
        CycleDepService1 cycleDepService1 = context.getBean(CycleDepService1.class);

        cycleDepService1.run();



    }
}
