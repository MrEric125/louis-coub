package com.louis.mybatis.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author John·Louis
 * @date create in 2019/8/31
 * description:
 */
@Data
public class LocalUser {

    private String username;

    private long id;

    private int age;

    private String fivarite;



}
