package com.louis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author louis
 * @date 2022/6/28
 */
@Slf4j
@Service
public class SpringBeanUtils {

    private static ApplicationContext applicationContext;

    private static BeanFactory beanFactory;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringBeanUtils.applicationContext = applicationContext;
        if (Objects.isNull(applicationContext)) {
            return;
        }
        AbstractApplicationContext context = (AbstractApplicationContext) applicationContext;
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        if (Objects.isNull(beanFactory)) {
            return;
        }
        SpringBeanUtils.beanFactory = beanFactory;
    }

    public static <T> T getBean(Class<T> clazz) {
        return beanFactory != null ? beanFactory.getBean(clazz) : null;
    }

    public static Object getBeanByString(String beanName) throws BeansException {
        return beanFactory != null ? beanFactory.getBean(beanName) : null;
    }

}
