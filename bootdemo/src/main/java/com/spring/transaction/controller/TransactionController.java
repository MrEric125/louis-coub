package com.spring.transaction.controller;

import com.spring.transaction.Tb1;
import com.spring.transaction.Tb1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
@RequestMapping("/trans")
@RestController
public class TransactionController {

    @Autowired
    private Tb1Repository tb1Repository;


    @PutMapping
    public String save() {
        return "ok";
    }


    @PostMapping("/update")
    public String update(@RequestBody Tb1 tb1) {


        tb1Repository.save(tb1);

        return "ok";
    }

}
