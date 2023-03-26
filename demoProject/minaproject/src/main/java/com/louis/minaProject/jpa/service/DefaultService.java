package com.louis.minaProject.jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
@Slf4j
@Service
public class DefaultService {

    @Transactional(propagation = Propagation.REQUIRED)
    public String add() {
        log.info("defaultService add");
        return "defaultService add";

    }

}
