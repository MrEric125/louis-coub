package com.louis.mybatis.dynamic.mapper;

import com.louis.mybatis.dynamic.base.BaseMapper;
import com.louis.mybatis.tkmybatis.entity.LocalUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public interface UserMapper extends BaseMapper<LocalUser,Long> {
    @Select("SELECT id,account,password FROM user")
    public List<LocalUser> list();
}
