package com.louis.mybatis.dynamic.base;

import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class BaseBuilder {

    private Class<?> getMapperClass(MappedStatement mappedStatement) {
        try {
            String mappedStatementId = mappedStatement.getId();
            String className = mappedStatementId.substring(0, mappedStatementId.lastIndexOf("."));
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?>[] getMapperGenerics(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        for (Type type : types) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (BaseMapper.class != (Class<?>) parameterizedType.getRawType())
                continue;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            Class<?>[] generics = new Class[typeArguments.length];
            for (int i = 0; i < typeArguments.length; i++)
                generics[i] = (Class<?>) typeArguments[i];
            return generics;
        }
        return null;
    }
}
