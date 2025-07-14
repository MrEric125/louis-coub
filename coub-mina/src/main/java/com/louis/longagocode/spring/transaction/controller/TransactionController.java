package com.louis.longagocode.spring.transaction.controller;

import com.louis.common.common.HttpResult;
import com.spring.transaction.Tb1;
import com.spring.transaction.Tb1Repository;
import com.spring.transaction.Tb1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 * 子线程能够查询到的数据是已经提交了的数据，
 */
@RequestMapping("/trans")
@RestController
public class TransactionController {

    @Autowired
    private Tb1Repository tb1Repository;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private Tb1Service tb1Service;


    @PutMapping("/save")

    public String save() {
        return "ok";
    }

    @GetMapping("select")
    public HttpResult select() {
        List<Tb1> all = tb1Repository.findAll();
        return HttpResult.ok(all);

    }

    @PostMapping("/updateById")
    public HttpResult updateById(Long id) throws Exception {
        return HttpResult.ok(tb1Service.update(id));
    }


    @PostMapping("/update")
    public String update(@RequestBody Tb1 tb1) {

        tb1Repository.save(tb1);

        return "ok";
    }

}
