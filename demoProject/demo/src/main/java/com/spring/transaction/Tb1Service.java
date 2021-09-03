package com.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
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
        return tb1Repository.save(tb1);
    }

    @Transactional
    public Tb1 update(Long id) {
        Optional<Tb1> byId = tb1Repository.findById(id);

        Tb1 tb1 = byId.get();


        tb1.setName(new Date().toString());
        Tb1 save = tb1Repository.save(tb1);
        taskExecutor.execute(()->{
            save.setName(new Date().toString());
            tb1Repository.save(save);
        });
        return save;
    }



}
