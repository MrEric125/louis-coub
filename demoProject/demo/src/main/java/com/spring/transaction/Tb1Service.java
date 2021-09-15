package com.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
@Service
public class Tb1Service {

    @Autowired
    private Tb1Repository tb1Repository;

    @Autowired
    private TaskExecutor taskExecutor;


    @Transactional
    public Tb1 save(Tb1 tb1) {
        Tb1 save = tb1Repository.save(tb1);
        throw new RuntimeException("save");
    }

    @Transactional(rollbackFor = Exception.class)
    public Tb1 update(Long id) throws Exception {
        Optional<Tb1> byId = tb1Repository.findById(id);

        Tb1 tb1 = byId.get();


        tb1.setName(new Date().toString());

//        try {
//            ((Tb1Service) AopContext.currentProxy()).save(tb1);
//        } catch (Exception e) {
//
//        }



        new Thread(()->{
            Tb1 tb2 = tb1;
            tb2.setName(new Date().toString());
            tb1Repository.save(tb2);
            throw new RuntimeException("runtime");
        }).start();
        Thread.sleep(2000);
        tb1Repository.save(tb1);
        return null;
    }

    @Transactional
    public Tb1 selectById(Long id) {
        Tb1 save2 = tb1Repository.findById(id).get();
        return save2;
    }



}
