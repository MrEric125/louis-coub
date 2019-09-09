package com.louis.bootmybatis.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author louis
 * <p>
 * Date: 2019/7/2
 * Description:
 */

@Slf4j
@Service
public class ConferenceServiceImpl implements ConferenceService{
    @Override
    public void conference() {

        Random random = new Random();
        int i = random.nextInt(10);
        int i1 = random.nextInt(10);
        int i2 = i1 / i;
        try {
            Thread.sleep(123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(" 这是一条测试日志");

        System.out.println("conference()");
    }
}
