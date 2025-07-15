package com.louis.longagocode.hystrix;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author John·Louis
 * @date create in 2019/8/24
 * description:
 */
@Slf4j
public class HystrixTest {


    @Test
    public void test() throws InterruptedException {
        HystrixCircuitBreakerCommand circuitBreakerCommand = null;

        for (int i = 0; i < 30; i++) {
            circuitBreakerCommand=new HystrixCircuitBreakerCommand(i);
            String execute = null;
            try {
                execute = circuitBreakerCommand.toObservable().toBlocking().toFuture().get();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("execute:>>>>>>>>>:{}",execute);
        }
        Thread.sleep(2000);
        System.out.println("====================================");
        log.info("休眠一段时间之后");
        System.out.println("====================================");
        for (int i = 31; i <40 ; i++) {
            circuitBreakerCommand=new HystrixCircuitBreakerCommand(i);
            String execute = null;
            try {
                execute = circuitBreakerCommand.execute();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("execute:>>>>>>>>>:{}",execute);

        }

    }
}
