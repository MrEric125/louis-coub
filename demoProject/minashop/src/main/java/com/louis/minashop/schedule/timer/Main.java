package com.louis.minashop.schedule.timer;

import java.util.Timer;

/**
 * @author John·Louis
 * @date created on 2020/3/18
 * description:
 */
public class Main {

    public static void main(String[] args) {
        Timer timer = new Timer();
        long delay1 = 1000;
        long period1 = 1000;
        timer.schedule(new TimerTaskService(), delay1, period1);

    }
}
