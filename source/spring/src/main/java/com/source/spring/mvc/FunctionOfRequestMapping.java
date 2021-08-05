package com.source.spring.mvc;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import com.source.spring.argumentResolver.Searchable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jun.liu
 * @since 2020/11/14 14:21
 * 主要问题：
 *  如何解析 httpUrl
 *  如何拼接各种url
 *  @see #requestBody(DemoParam)  如何解析 {@link org.springframework.web.bind.annotation.RequestBody}
 *
 *
 *  @see #responseBody(String) 如何解析 {@link org.springframework.web.bind.annotation.ResponseBody}
 *
 *
 */
@Controller
public class FunctionOfRequestMapping {

    private static final String SUCCESS = "success";


    /**
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
     *
     *
     * @return
     */
    @RequestMapping("/testRequestMapping")
    public HttpResult testRequestMapping(@RequestParam()String test) {
        return HttpResult.ok(test);
    }


    /**
     * how to resolve {@link org.springframework.web.bind.annotation.RequestBody}
     *
     * @see org.springframework.http.converter.HttpMessageConverter
     * <a> https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/integration.html#rest-message-conversion</a>
     *
     * 事实上 在url 调用的过程中 涉及到的类
     *
     * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter}
     *
     * @see org.springframework.web.method.support.InvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)
     *
     *
     *解析{@link RequestBody}
     * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)
     * @see org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter#handle(HttpServletRequest, HttpServletResponse, Object)
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod)
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#invokeHandlerMethod(HttpServletRequest, HttpServletResponse, HandlerMethod)
     * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#invokeAndHandle(ServletWebRequest, ModelAndViewContainer, Object...)
     * 真正解析参数的地方
     * @see org.springframework.web.method.support.InvocableHandlerMethod#invokeForRequest(NativeWebRequest, ModelAndViewContainer, Object...)
     * 通过反射的方式执行方法
     * @see org.springframework.web.method.support.InvocableHandlerMethod#doInvoke(Object...)

     */

    @RequestMapping("/requestBody")
    @ResponseBody
    public HttpResult requestBody() {
        return HttpResult.ok();
    }
    @RequestMapping("/requestParam")
    @ResponseBody
    public HttpResult requestParam1(@RequestParam String id,@RequestParam String name) {
        return HttpResult.ok(JSON.toJSONString(id));
    }




    /**
     * how to resolve {@link org.springframework.web.bind.annotation.ResponseBody}
     *
     * @see org.springframework.http.converter.HttpMessageConverter
     * <a>https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/integration.html#rest-message-conversion</a>
     *
     * 解析{@link ResponseBody}
     *
     *前面步骤同{@link this#requestBody(DemoParam)} 中的{@link RequestBody}解析过程
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#invokeAndHandle(ServletWebRequest, ModelAndViewContainer, Object...)
     *
     * @see org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor#writeWithMessageConverters(Object, MethodParameter, ServletServerHttpRequest, ServletServerHttpResponse)
     *
     *  如果响应body 不为空 则
     * @see org.springframework.http.converter.HttpMessageConverter#write(Object, MediaType, HttpOutputMessage)

     *
     *
     */
    @ResponseBody
    @RequestMapping("responseBody")
    public HttpResult responseBody(@RequestParam String request) {
        return HttpResult.ok(request);
    }

    @ResponseBody
    @RequestMapping("/customizeArgumentResolver")
    public HttpResult customizeArgumentResolver(@Searchable ParamInParam paramInParam) {
        return HttpResult.ok(JSON.toJSONString(paramInParam));
    }

}
