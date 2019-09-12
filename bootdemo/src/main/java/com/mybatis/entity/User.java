package com.mybatis.entity;

import com.mybatis.Table;
import lombok.Data;

/**
 * @author louis
 * <p>
 * Date: 2019/9/10
 * Description:
 */
@Data
@Table(name = "test_user")
public class User {

    private long id;
    private String name;
    private int type;

}

