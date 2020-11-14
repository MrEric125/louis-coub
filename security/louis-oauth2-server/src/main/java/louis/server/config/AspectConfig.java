package louis.server.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author louis
 * <p>
 * Date: 2019/11/28
 * Description:
 */
@Slf4j
@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {



    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();



    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logPointcut() {
        //不做任何处理
    }


    @Before("logPointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        Signature signature = joinPoint.getSignature();
        String fromClassName = "";
        String targetMethod = "";
        if ((signature instanceof MethodSignature)) {
//            throw new IllegalArgumentException("该注解只能用于方法");
            MethodSignature msig = (MethodSignature) signature;
            Object target = joinPoint.getTarget();
            Method currentMethod = null;
            try {
                currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (currentMethod != null) {
                targetMethod = currentMethod.getName();
                Class<?> declaringClass = currentMethod.getDeclaringClass();
                fromClassName = declaringClass.getName();
            }
        }
        log.info("<<<<<<调用方法开始");
        log.info("methodName:{}.{}",fromClassName,targetMethod);
    }


    @After("logPointcut()")
    public void writeLog(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String host = request.getHeader("host");
        String uri = request.getRequestURI();
        Signature signature = joinPoint.getSignature();
        String fromClassName = "";
        String targetMethod = "";
        String returnType = "";
        if ((signature instanceof MethodSignature)) {
//            throw new IllegalArgumentException("该注解只能用于方法");
            MethodSignature msig = (MethodSignature) signature;
            Object target = joinPoint.getTarget();
            Method currentMethod = null;
            try {
                currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (currentMethod != null) {
                targetMethod = currentMethod.getName();
                Class<?> declaringClass = currentMethod.getDeclaringClass();
                fromClassName = declaringClass.getName();
                returnType = currentMethod.getReturnType().getName();
            }
        }
        long startTime = threadLocal.get();
        long endTime = System.currentTimeMillis();
        log.info("调用结束：>>>>>>");
        log.info("host:{},url:{},usedTime:{}", host, uri, endTime - startTime);
        log.info(" fromClass:{},currentMethod:{},returnType:{}",  fromClassName, targetMethod, returnType);
    }
}
