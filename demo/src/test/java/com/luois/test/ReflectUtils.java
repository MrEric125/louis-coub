package com.luois.test;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author John·Louis
 * @date created on 2020/3/3
 * description:
 */
public class ReflectUtils {


    /**
     * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
     *
     * @param targetClass      目标类
     * @param targetMethod      方法名
     * @param   parameterType  方法参数类型数组
     * @return 方法对象
     * @throws Exception
     */
    public static Method getParentPrivateMethod(Class<?> targetClass, String targetMethod, final Class[] parameterType) {
        Method method = null;
        try {
            method = targetClass.getDeclaredMethod(targetMethod, parameterType);
        } catch (NoSuchMethodException e) {
            try {
                method = targetClass.getMethod(targetMethod, parameterType);
            } catch (NoSuchMethodException ex) {
                if (targetClass.getSuperclass() == null) {
                    return method;
                } else {
                    method = getParentPrivateMethod(targetClass.getSuperclass(), targetMethod,
                            parameterType);
                }
            }
        }
        return method;
    }

    /**
     *
     * @param targetObj   调整方法的对象
     * @param targetMethod    方法名
     * @param parameterType    参数类型数组
     * @param parameter    参数数组
     * @return 方法的返回值
     */
    public static Object invoke(Object targetObj, final String targetMethod,
                                 final Class[] parameterType , final Object... parameter) {
        try {

            Method method =ReflectionUtils.findMethod(targetObj.getClass(), targetMethod, parameterType);
            if (method != null) {
                ReflectionUtils.makeAccessible(method);

                return ReflectionUtils.invokeMethod(method, targetObj, parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
