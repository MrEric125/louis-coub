package com.source.spring.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.config.BeanPostProcessor;
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
 * 需要注意的是，在创建代理类之前，系统是需要知道哪些类需要被代理到我们指定的切面中去的，这里需要首先找到切面中的织入(Advisor)
 *
 * 通过在哪里找的呢？
 *
 * {@link org.springframework.aop.aspectj.annotation.BeanFactoryAspectJAdvisorsBuilder#buildAspectJAdvisors}
 *
 * 找到对应的切面点，并且也有对应的织入(Advisor)的时候，那么就需要创建代理类了
 *
 * 在这里就准备创建我们的代理对象
 * {@link AbstractAutoProxyCreator#createProxy(Class, String, Object[], TargetSource)}
 *
 * 这里还有几个问题，
 * 1. 我们创建的代理对象就是什么样的，代理对象的代理方法一般会在什么时候调用呢？等等吧
 *
 * 调用的话当时是在我们调用代理类的代理方法的时候，其实我们在最后getBean()获取到的那个bean并不是我们我们真正创建的bean,而是被spring代理的bean
 *
 * 需要注意的是，我们在使用AOP的时候往往会切入到某个方法上，，并且切入的方式有前置的，后置的，环绕的，这些都是怎么实现的呢？
 *
 *1. 如果 发现有advice 则会创建代理对象，讲代理对象放在{@link AbstractAutoProxyCreator#proxyTypes} 中，后期我们从Context中取的对象就是这里存进去的对象
 *
 *织入的时间，BeanPostProcessor获取的时间。
 *
 *
 * ===================================
 * 如果让你自己设计一个aop 框架会怎么设计？
 * 1. 这个aop应该是个开关对象，有的项目中需要添加，有的项目中可能不需要添加aop。 invokeBeanFactoryPostProcessors（）中执行
 * 1. 首先拿到这个目标对象
 * 2. 找到切面点，判断当前对象是否为代理对象
 * 3. 如果当前对象为代理对象，代理的方法有哪些？
 * 4. 创建代理对象，放入ioc管理，后期执行就执行的这个代理对象中的代理方法。
 *
 *
 */
public class AopApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //切面配置类要么被手动加入上下文，要么通过注解的方式加入上下文，否则不生效
        // todo 为什么这里需要将 AopAspect 这个放入到spring环境管理中去
        context.register(AopAspect.class);
        context.scan("com.source.spring.aop");

        context.refresh();

        AopEntity aopEntity = context.getBean("aopEntity", AopEntity.class);
        aopEntity.test();
        EntityService en = context.getBean(EntityService.class);
        System.out.println(en.test());
        System.out.println(JSON.toJSONString(aopEntity, true));

        context.close();
    }

}
