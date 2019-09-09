package com.louis.mybatis.mapper;


import com.louis.mybatis.entity.LocalUser;

/**
 * @author John·Louis
 * @date create in 2019/8/31
 * description:
 */
public interface UserMapper {

    LocalUser getUser(long id);


    int inserUser(LocalUser user);
}
