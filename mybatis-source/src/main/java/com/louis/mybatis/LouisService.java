package com.louis.mybatis;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
public class LouisService {

    @Autowired
    LouisMapper louisMapper;
    public void select() {
        louisMapper.selectByPrimaryKey(1);
    }
}
