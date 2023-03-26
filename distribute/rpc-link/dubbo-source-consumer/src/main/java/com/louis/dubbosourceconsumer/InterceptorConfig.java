package com.louis.dubbosourceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author louis
 * @date 2022/7/26
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TraceIdHandlerInterceptor traceIdHandlerInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        // 这个拦截器最后执行(异常拦截的日志中能用到traceId)
        registry.addInterceptor(traceIdHandlerInterceptor).addPathPatterns("/**").order(-1);

        super.addInterceptors(registry);

    }
}
