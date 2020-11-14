package com.luois.http;

/**
 * @author louis
 * <p>
 * Date: 2019/11/1
 * Description:
 */
@FunctionalInterface
public interface CheckFunction<T, R> {
    R apply(T t) throws Exception;
}
