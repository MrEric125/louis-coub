package com.louis.minashop.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
@Service
public class DefaultService {

    @Transactional(propagation = Propagation.REQUIRED)
    public void add() {

    }

}
