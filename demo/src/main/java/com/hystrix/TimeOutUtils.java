package com.hystrix;

import java.util.Random;

/**
 * @author John·Louis
 * @date create in 2019/8/24
 * description:
 */
public class TimeOutUtils {


    public static int randomlyRunLong(int bound,long sleepTime) {
        Random random = new Random();
        int randomNum = random.nextInt((bound - 1) + 1) + 1;
        if (bound==randomNum) sleep(sleepTime);
        return randomNum;


    }

    private static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
