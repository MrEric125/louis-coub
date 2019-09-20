package com.louis.mybatis.dynamic.mapper;


import com.louis.mybatis.dynamic.entity.LocalUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/8/31
 * description:
 */

public interface UserMapper {

    LocalUser getUser(long id);


    int inserUser(LocalUser user);

//    @Results(id = "localresultMap",value = {
//            @Result(property = "id",column = "id",id = true),
//            @Result(property = "age",column = "age"),
//            @Result(property = "username",column = "username"),
//            @Result(property = "fivarite",column = "fivarite")
//
//    })
//    @ResultMap("localresultMap")
    @Select("select * from local_user;")
    List<LocalUser> selectAll();

    @Insert("insert into `local_user` (id, username, age, fivarite) VALUES (#{id},#{username},#{age},#{fivarite})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int inser2(LocalUser localUser);








}
