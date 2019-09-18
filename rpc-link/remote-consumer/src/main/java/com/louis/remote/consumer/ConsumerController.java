package com.louis.remote.consumer;

import com.louis.remote.api.DefautDemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("consumer")
public class ConsumerController {

    @Reference
    private DefautDemoService defautDemoService;

    @GetMapping("/")
    public String consumer() {
        return defautDemoService.sayHello("louis");
    }


}
