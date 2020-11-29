package com.source.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;

/**
 * <a>
 *     https://www.cnblogs.com/java-chen-hao/p/11187914.html
 * </a>
 * @author jun.liu
 * @date created on 2020/11/17
 * description:
 * controller中的方法被封装成
 *
 * @see org.springframework.stereotype.Controller
 */
@Slf4j
public class MyServletInvocableHandlerMethod extends ServletInvocableHandlerMethod {
    public MyServletInvocableHandlerMethod(Object handler, Method method) {
        super(handler, method);
        log.info("1");
    }

    public MyServletInvocableHandlerMethod(HandlerMethod handlerMethod) {
        super(handlerMethod);
        log.info("2");
    }


    /**
     * 获取方法请求的参数
     * @param request
     * @param mavContainer
     * @param providedArgs
     * @return
     * @throws Exception
     */
    @Override
    protected Object[] getMethodArgumentValues(NativeWebRequest request, ModelAndViewContainer mavContainer, Object... providedArgs) throws Exception {
        log.info("1");
        return super.getMethodArgumentValues(request, mavContainer, providedArgs);
    }
}
