package com.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @author louis
 * @Date 2020/1/17
 * description:
 */
@Component
public class LifeCycle
        implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean
{

    @PostConstruct
    public void init() {
        System.out.println("init");
    }
    public void user() {
        System.out.println("use method");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(" set bean factory");

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("set bean name"+name);

    }

    @Override
    public void destroy() throws Exception {

        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after properties set");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("application context");


    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("post process before");
//        return bean;
//    }

//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("post process after");
//        return bean;
//    }
}
