package com.source.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * @author jun.liu
 * @date created on 2020/11/17
 * description:
 * @see org.springframework.web.bind.annotation.RequestBody
 * @see org.springframework.web.bind.annotation.ResponseBody
 */
@Slf4j
public class MyRequestResponseBodyMethod extends RequestResponseBodyMethodProcessor {
    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters) {
        super(converters);
        log.info("4");
    }

    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager) {
        super(converters, manager);
        log.info("1");
    }

    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice) {
        super(converters, requestResponseBodyAdvice);
        log.info("2");
    }

    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager, List<Object> requestResponseBodyAdvice) {
        super(converters, manager, requestResponseBodyAdvice);
        log.info("3");
    }
}
