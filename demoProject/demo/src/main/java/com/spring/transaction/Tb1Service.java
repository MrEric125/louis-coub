package com.spring.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
@Service
public class Tb1Service {

    private Tb1Repository tb1Repository;


    @Transactional
    public Tb1 save(Tb1 tb1) {
        return tb1Repository.save(tb1);
    }



}
