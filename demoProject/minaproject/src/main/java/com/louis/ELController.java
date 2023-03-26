package com.louis;

import com.louis.minaProject.jpa.service.DefaultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * @date 2022/7/31
 */
@Slf4j
@RestController
@RequestMapping("el")
public class ELController {

    @Autowired
    private Environment systemEnvironment;

    @Autowired
    private DefaultService defaultService;


    @Value("#{defaultService.add}")
    private String elResult;

    @RequestMapping("elConsole")
    public void elConsole() {
        log.info("cycleBufferSize:{}", elResult);

    }


}
