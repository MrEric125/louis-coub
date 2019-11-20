package com.louis.controller;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public Wrapper insert() {
        return WrapMapper.ok("this is insert");
    }
    @RequestMapping("/select")
    public Wrapper select() {
        return WrapMapper.ok("this is select");
    }


}
