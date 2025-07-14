package com.louis.longagocode.hystrix;

import lombok.extern.slf4j.Slf4j;

/**
 * @author John·Louis
 * @date create in 2019/8/25
 * description:
 */
@Slf4j
public class FallbackMethod {

    public String fallback(int num, long sleepTime) {
        log.info("fallback");
        return num + sleepTime + "\n fallback";
    }
}
