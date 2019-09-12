package com.mybatis.controller;

import com.mybatis.entity.User;
import com.mybatis.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/9/10
 * Description:
 */
@RestController
public class LoginController {

//    @Autowired
//    private IUserMapper iUserMapper;
//
//    //新增
//    @ResponseBody
//    @RequestMapping(value = "/add")
//    public Integer add() {
//        User userEntity = new User();
//        userEntity.setName("test");
//        userEntity.setType(3);
//        Integer num = iUserMapper.add(userEntity);
//        return num;
//    }
//
//    //修改
//    @ResponseBody
//    @RequestMapping(value = "/update")
//    public Integer update() {
//        User userEntity = new User();
//        userEntity.setId(23);
//        userEntity.setName("test");
//        userEntity.setType(3);
//        Integer num = iUserMapper.update(userEntity);
//        return num;
//    }
//
//    //查询
//    @ResponseBody
//    @RequestMapping(value = "/query")
//    public User query() {
//        User tPermissionEntity = new User();
//        tPermissionEntity.setId(23);
//        tPermissionEntity= (User) iUserMapper.get(tPermissionEntity);
//        return tPermissionEntity;
//    }
//
//    //删除
//    @ResponseBody
//    @RequestMapping(value = "/delete")
//    public Integer delete() {
//        User userEntity = new User();
//        userEntity.setId(22);
//        Integer num = iUserMapper.delete(userEntity);
//        return num;
//    }

}
