package com.source.spring.rpc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author jun.liu
 * @since 2021/5/18 17:30
 * {@literal https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/integration.html#remoting }
 * <p>
 * RMI
 * {@link org.springframework.remoting.rmi.RmiProxyFactoryBean}
 * {@link org.springframework.remoting.rmi.RmiServiceExporter}
 * <p>
 * HTTP invoke
 * {@link org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean}
 * {@link org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter}
 * <p>
 * Hession
 * {@link org.springframework.remoting.caucho.HessianProxyFactoryBean}
 * {@link org.springframework.remoting.caucho.HessianServiceExporter}
 * <p>
 * JAX-ws
 * <p>
 * JMS
 * {@link JmsInvokerServiceExporter }
 * {@link JmsInvokerProxyFactoryBean }
 */
@Configuration
public class SpringRpcMessage implements ApplicationContextAware {

    private ApplicationContext applicationContext;

//    @Bean
//    public RmiServiceExporter rmiServiceExporter() {
//        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
//
//
//        rmiServiceExporter.setServiceName("AccountService");
//        rmiServiceExporter.setService(applicationContext.getBean(AccountService.class));
//        rmiServiceExporter.setServiceInterface(AccountService.class);
//        rmiServiceExporter.setRegistryPort(1199);
//
//        return rmiServiceExporter;
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
