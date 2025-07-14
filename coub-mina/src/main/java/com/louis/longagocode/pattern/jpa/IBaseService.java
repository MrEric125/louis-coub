package com.louis.longagocode.pattern.jpa;

/**
 * @author jun.liu
 * @date created on 2020/7/18
 * description:
 */
public interface IBaseService<T,S> {

    String queryById(String id);


    /**
     * 通过某个方法查询出来的结果缓存
     * 需要解决的问题：
     * 缓存到的key是哪个?
     * 调用的方法是哪一个
     *
     * @param methodName
     * @param paramTypes
     * @return
     */
    void cacheByMethod(String key,String methodName, Class<?>... paramTypes);



}
