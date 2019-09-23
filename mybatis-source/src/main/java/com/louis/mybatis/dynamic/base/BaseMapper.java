package com.louis.mybatis.dynamic.base;

import org.apache.ibatis.annotations.*;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public interface BaseMapper<T,K> {
    @InsertProvider(type = SqlSourceBuilder.class, method = "build")
     Long insert(T model);

    @UpdateProvider(type = SqlSourceBuilder.class, method = "build")
     Long updateById(T model);

    @DeleteProvider(type = SqlSourceBuilder.class, method = "build")
     Long deleteById(@Param("id") K id);

    @SelectProvider(type = SqlSourceBuilder.class, method = "build")
     T getById(@Param("id") K id);

    @SelectProvider(type = SqlSourceBuilder.class, method = "build")
     Boolean existById(@Param("id") K id);
}
