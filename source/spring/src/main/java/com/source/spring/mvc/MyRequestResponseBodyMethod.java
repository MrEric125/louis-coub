package com.source.spring.mvc;

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
 *
 */
public class MyRequestResponseBodyMethod extends RequestResponseBodyMethodProcessor {
    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager) {
        super(converters, manager);
    }

    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice) {
        super(converters, requestResponseBodyAdvice);
    }

    public MyRequestResponseBodyMethod(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager, List<Object> requestResponseBodyAdvice) {
        super(converters, manager, requestResponseBodyAdvice);
    }
}
