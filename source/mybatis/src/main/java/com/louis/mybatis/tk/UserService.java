package com.louis.mybatis.tk;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 事务不生效的集中类型
     * 1. 抛出异常不是{@link RuntimeException} 的子类
     *
     * @param userInfo
     * @param throwable
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public UserInfo insertUser(UserInfo userInfo, boolean throwable) throws Exception {

        int insert = userInfoMapper.insert(userInfo);
        if (throwable) {
            throw new RuntimeException("插入数据报错");
        }
        return userInfo;
    }
}
