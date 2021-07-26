package com.source.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl implements EntityService {

    public String test(){
        System.out.println("aopEntiity");
        return "aopentity";
    }
}
