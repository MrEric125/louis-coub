package com.louis.mybatis.dynamic.mapper;

import com.louis.mybatis.dynamic.entity.BaseEntity;
import com.louis.mybatis.dynamic.entity.LocalUser;
import com.louis.mybatis.dynamic.provider.LocalUserProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 */
public interface BaseMapper<T extends BaseEntity> {

    @SelectProvider(type = LocalUserProvider.class, method = "selectById")
    LocalUser selectById(final long id);

    @InsertProvider(type = LocalUserProvider.class,method = "insert")
    int insert(T localUser);

    @UpdateProvider(type = LocalUserProvider.class, method = "updateById")
    int update(T localUser);

    @DeleteProvider(type = LocalUserProvider.class, method = "deleteById")
    int deleteById(final Long id);
}
