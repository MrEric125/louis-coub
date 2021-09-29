package com.louis.mybatis.tk;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Transactional
    public UserInfo insertUser(UserInfo userInfo, boolean throwable) throws Exception {

        int insert = userInfoMapper.insert(userInfo);
        if (throwable) {
            throw new Exception("插入数据报错");
        }
        return userInfo;
    }
}
