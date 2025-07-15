package com.louis.longagocode.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * @Date 2020/1/17
 * description:
 */
@RequestMapping("/lifecycle")
@RestController
public class LifeCycleController {
    @Autowired
    private LifeCycle lifeCycle;

    @RequestMapping("/use")
    public String lifeCycle() {
        lifeCycle.user();
        return "success";
    }
}
