package com.louis.dubbosourceconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author louis
 * @date 2022/5/25
 * s生成traceId 拦截器
 */
@Component
@Slf4j
public class TraceIdHandlerInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TraceIdUtils.generateTraceIdToRequest(request);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TraceIdUtils.removeTraceId();
    }

}