package com.louis.mybatis.tkmybatis.controller;

import com.louis.mybatis.tkmybatis.entity.LocalUser;
import com.louis.mybatis.tkmybatis.mapper.LocalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
@RestController
public class LouisController {

    @Autowired
    LocalUserMapper louisMapper;

    @RequestMapping("/select/{id}")
    public LocalUser select(@PathVariable Long id) {
        return louisMapper.selectByPrimaryKey(id);
    }
}
