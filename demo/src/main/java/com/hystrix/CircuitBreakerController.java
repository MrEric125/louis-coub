package com.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * @author John·Louis
 * @date create in 2019/8/24
 * description:
 */
@Slf4j
@RestController
public class CircuitBreakerController {


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })
    @GetMapping ("/circuit")
    public String circuit(@RequestParam int num,@RequestParam long sleepTime) {
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);
        return runLong + "";

    }

    @GetMapping("fallback")
    @HystrixCommand(fallbackMethod = "fallback")
    public String hystrixFallback(@RequestParam int num,@RequestParam long sleepTime) {
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);

        return runLong + "";
    }


    @GetMapping("/bulkhead")
    @HystrixCommand(fallbackMethod = "fallback",threadPoolKey = "bulkhead",threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "30"),
            @HystrixProperty(name = "maxQueueSize",value = "10")
    })
    public String bulkhead(@RequestParam int num,@RequestParam long sleepTime) {
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);

        return runLong + "";
    }

    public String fallback(String nums, long sleepTime) {
        log.info("fallback");
        return nums + sleepTime + "\n fallback";
    }



}
