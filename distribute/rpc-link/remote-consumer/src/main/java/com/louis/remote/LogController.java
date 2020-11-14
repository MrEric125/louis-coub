package com.louis.remote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/log/{path}")
    public String log(@PathVariable String path) {
        log.info("this is info{}",path);
        log.error("this is error{}",path);
        log.debug("this is debug{}",path);
        log.warn("this is warn{}",path);
        return path;
    }
}
