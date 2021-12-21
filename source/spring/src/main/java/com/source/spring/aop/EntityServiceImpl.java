package com.source.spring.aop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Data
public class EntityServiceImpl  {

    @AutoSysConfig
    @Value("${domain.url:}")
    private String domainUrl;



    public String test(){
        System.out.println("aopEntiity");
        return "aopentity";
    }
}
