package com.source.spring.aop;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class AutoSysConfigAspect {


    @Pointcut("execution(* com.source.spring.aop.EntityServiceImpl.get*(..))")
    public void pointCut() {

    }

    @Around(" pointCut()")
    public Object afterReturning(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();


        Object proceedReturn = null;
        try {
            proceedReturn = joinPoint.proceed();

        } catch (Throwable throwable) {
            log.error("切面数据", throwable);
        }
        Field field = getFieldFromGetMethodName(target, methodName);

        String configValue = getConfigValue(target, field);
        log.info("方法名：{}：原始属性注入值：{}; 新属性注入值：{}；", methodName, JSON.toJSONString(proceedReturn), JSON.toJSONString(configValue));

        if (StringUtils.isEmpty(configValue)) {
            return proceedReturn;
        }
        return configValue;
    }

    /**
     * 获取当前对象以及其父类中 某个get方法或者set方法的属性
     *
     * @param targetObj
     * @param methodName
     * @return
     */
    private Field getFieldFromGetMethodName(Object targetObj, String methodName) {

        String generalField = getGeneralField(methodName);
        if (generalField == null) {
            return null;

        }
        Class clazz = targetObj.getClass();

        while (clazz != null) {
            Field declaredField = null;
            try {
                declaredField = clazz.getDeclaredField(generalField);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            if (declaredField != null) {
                return declaredField;
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    public static String removePreAndLowerFirst(CharSequence str, int preLength) {
        if (str == null) {
            return null;
        }
        if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            if (str.length() > preLength + 1) {
                return first + str.toString().substring(preLength + 1);
            }
            return String.valueOf(first);
        } else {
            return str.toString();
        }
    }

    public static String getGeneralField(CharSequence getOrSetMethodName) {
        final String getOrSetMethodNameStr = getOrSetMethodName.toString();
        if (getOrSetMethodNameStr.startsWith("get") || getOrSetMethodNameStr.startsWith("set")) {
            return removePreAndLowerFirst(getOrSetMethodName, 3);
        } else if (getOrSetMethodNameStr.startsWith("is")) {
            return removePreAndLowerFirst(getOrSetMethodName, 2);
        }
        return null;
    }


    /**
     * 获取这个对象上，这个属性值，
     *
     * @param obj
     * @param targetField
     * @return
     */
    private String getConfigValue(Object obj, Field targetField) {

        if (targetField == null) {
            return null;
        }
        if (obj == null) {
            return null;
        }
        AutoSysConfig annotation = targetField.getAnnotation(AutoSysConfig.class);

        String defaultTargetFieldValue = null;

        try {
            targetField.setAccessible(true);
            Object fieldValue = targetField.get(obj);
            if (fieldValue != null) {
                defaultTargetFieldValue = fieldValue.toString();
            }
            if (annotation != null) {
                Value valueAnnotation = targetField.getAnnotation(Value.class);

                String value = valueAnnotation.value();
                if (StringUtils.isBlank(value)) {
                    return null;
                }
                value = value.replace("$", "")
                        .replace("{", "")
                        .replace("}", "")
                        .replace(":", "")
                        .replace(".", "_");

                return StringUtils.isBlank(value) ? null : value;

            }
        } catch (Exception e) {

            log.info("动态获取属性值异常", e);
        }
        return defaultTargetFieldValue;


    }
}
