package com.louis.mybatis;

import com.louis.mybatis.dynamic.entity.LocalUser;
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
    LouisMapper louisMapper;

    @RequestMapping("/select")
    public LocalUser select(@PathVariable Long id) {
        return louisMapper.selectByPrimaryKey(id);
    }
}
