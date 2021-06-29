package com.source.spring.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jun.liu
 * @date created on 2020/9/4
 * description:
 *
 * spring AOP启动过程
 * xml
 * 在AopNamespaceHandler中的init() 会new {@link org.springframework.aop.config.AspectJAutoProxyBeanDefinitionParser}
 *
 * AspectJAutoProxyBeanDefinitionParser 又会注册一个 internalAutoProxyCreator{@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator}
 *
 * 注解的方式
 * 在 {@link EnableAspectJAutoProxy} 中会执行  AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);同样会创建一个 internalAutoProxyCreator
 *
 * 接下来xml与注解的形式都是一样的，主要就是看 AnnotationAwareAspectJAutoProxyCreator 的作用是什么？
 *
 * AnnotationAwareAspectJAutoProxyCreator 实现了{@link BeanPostProcessor },所以当程序加载我们代理的bean的时候，
 *
 * 那么就会执行{@link AbstractAdvisorAutoProxyCreator#postProcessAfterInitialization(Object, String)}  }
 *
 * 在这里就准备创建我们的代理对象
 * {@link AbstractAutoProxyCreator#createProxy(Class, String, Object[], TargetSource)}∑
 *
 * 这里还有几个问题，
 * 1. 我们创建的代理对象就是什么样的，代理对象的代理方法一般会在什么时候调用呢？等等吧
 *
 * 需要注意的是，我们在使用AOP的时候往往会切入到某个方法上，，并且切入的方式有前置的，后置的，环绕的，这些都是怎么实现的呢？
 *
 *
 *
 *
 *
 *
 */
public class AopApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //切面配置类要么被手动加入上下文，要么通过注解的方式加入上下文，否则不生效
        context.register(AopAspect.class);
        context.scan("com.source.spring.aop");

        context.refresh();

        AopEntity aopEntity = context.getBean("aopEntity", AopEntity.class);
        aopEntity.test();
        EntityService en = context.getBean(EntityService.class);
        System.out.println(en.aopentity());
        System.out.println(JSON.toJSONString(aopEntity, true));
    }

}
