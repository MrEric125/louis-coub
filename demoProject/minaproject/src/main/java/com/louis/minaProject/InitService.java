package com.louis.minaProject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author jun.liu
 * @date created on 2020/6/17
 * description:
 */
@Slf4j
@Service
public class InitService implements InitializingBean {


    @PostConstruct
    public void init() {
        log.info("init");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");

    }
}
