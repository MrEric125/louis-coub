package com.louis.bootmybatis.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author louis
 * <p>
 * Date: 2019/7/2
 * Description:
 */

@Configuration
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {

    protected Logger requestLog = LoggerFactory.getLogger("REQUEST");
    protected Logger loginLog = LoggerFactory.getLogger("LOGIN");


    private ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    public static final String SYSCODE = "SF-SYSCODE";
    public static final String MODELNAME = "test_web";
    public static final String LOGIN = "login";





//    @Pointcut("execution(* com.BootMybatisApplication.*(..))")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void conference() {}

    @Before("conference()")
    public void before() {
        this.threadLocal.set(new Date(System.currentTimeMillis()));
    }


    /**
     * 开会之前--找个位置坐下
     */
    @After("conference()")
    public void takeSeats(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
//        Method method = getMethodByClassAndName(target.getClass(), methodName);
//        RequestMapping requestMapping= (RequestMapping) getAnnotationByMethod(method, RequestMapping.class);
//        log.info("requestMapping 请求地址：{}",requestMapping.value());
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        String host = httpServletRequest.getHeader("host");
        String uri = httpServletRequest.getRequestURI();
        int status = httpServletResponse.getStatus();
        final Date startTime = this.threadLocal.get();
        final Date endTime = new Date(System.currentTimeMillis());
        if (StringUtils.equals("/login", uri)) {
            loginLog.info("   login=====>    {},{},{},{},{},{}", SYSCODE, MODELNAME, host, uri, status, endTime.getTime() - startTime.getTime());
            Object[] args = joinPoint.getArgs();
            Arrays.stream(args).forEach(x -> loginLog.info("参数为：{}", x));
        } else {
            requestLog.info("    request=====>  {},{},{},{},{},{}", SYSCODE, MODELNAME, host, uri, status, endTime.getTime() - startTime.getTime());
            Object[] args = joinPoint.getArgs();
            Arrays.stream(args).forEach(x -> requestLog.info("request====>参数为：{}", x));
        }



    }

   /* *//**
     * 开会之前--手机调成静音
     *//*
    @Before("conference()")
    public void silenceCellPhones() {
        log.info("before no jointPoint");
    }*/

 /*   *//**
     * 开会之后--写会议总结报告
     *//*
    @After("conference()")
    public void summary() {
        log.info("after with no arg");
    }*/

    private Annotation getAnnotationByMethod(Method method, Class annoClass) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if ( annoClass==annotation.annotationType() ) {
                return annotation;

            }
        }
        return null;
    }

    /**
     * 根据类和方法名得到方法
     */
    private Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }


}
