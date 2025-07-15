package com.louis.longagocode.concurrent.executor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author louis
 * <p>
 * Date: 2019/8/21
 * Description:
 */
@Slf4j
public class ExecutorsTest {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ThreadUtils utils = new ThreadUtils();
            String s = String.valueOf(i);
            log.info(utils.toString());
            utils.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            log.info("sout:{}", s);

                        }
                    }
                    , 10);
//            utils.shutdown();
        }


    }
}
