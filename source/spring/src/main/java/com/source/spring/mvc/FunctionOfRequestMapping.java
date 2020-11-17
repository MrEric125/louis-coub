package com.source.spring.mvc;

import com.louis.common.common.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.liu
 * @since 2020/11/14 14:21
 * 主要问题：
 *  如何解析 httpUrl
 *  如何拼接各种url
 *  @see #requestBody()  如何解析 {@link org.springframework.web.bind.annotation.RequestBody}
 *
 *
 *  @see #responseBody() 如何解析 {@link org.springframework.web.bind.annotation.ResponseBody}
 *
 *
 */
@RestController
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
     * @see org.springframework.http.converter.HttpMessageConverter
     * <a> https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/integration.html#rest-message-conversion</a>
     */
    public void requestBody() {

    }
    public void responseBody() {

    }

}
