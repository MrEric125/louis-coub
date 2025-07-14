package com.louis.minaProject;

import com.google.common.collect.ImmutableMap;
import com.louis.common.common.HttpResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public HttpResult handlerMethod(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        Map<String, String[]> parameterMap = request.getParameterMap();

        Map<String, Object> map = ImmutableMap
                .of("requestURI", requestURI, "method", method, "requestParam", parameterMap, "errorMessage", ex.getMessage()!=null?ex.getMessage():"null");
        ex.printStackTrace();

        log.info("============>>>>>  {}", map);


        return HttpResult.ok(map);



    }
}
