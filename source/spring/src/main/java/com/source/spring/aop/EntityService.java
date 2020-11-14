package com.source.spring.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jun.liu
 * @date 2020/9/7 17:49
 */
@Service
public class EntityService {

    @Autowired
    private AopEntity aopEntity;

    public String aopentity() {
        return JSON.toJSONString(aopEntity, true);
    }

}
