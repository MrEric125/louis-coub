package com.source.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jun.liu
 * @date created on 2020/9/4
 * description:
 */
@EnableAspectJAutoProxy
@Aspect
//@Component
public class AopAspect {

    @Pointcut("execution(* *.test(..))")
    public void pointCut1() {

    }

    public void pointCut2() {

    }

    @Before("pointCut1()")
    public void beforeTest(JoinPoint joinPoint) {
        System.out.println("before test" + joinPoint.getSignature().getName());
    }

    @After("pointCut1()")
    public void afterTest(JoinPoint joinPoint) {
        System.out.println("after test" + joinPoint.getSignature().getName());
    }

    @AfterReturning("pointCut1()")
    public void afterReturn(JoinPoint joinPoint) {
        System.out.println("after return" + joinPoint.getSignature().getName());
    }

    @Around("pointCut1()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(" before around " + joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        System.out.println(" after around " + joinPoint.getSignature().getName());
        return proceed;

    }

}
