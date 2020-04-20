package com.louis.minashop.schedule.timer;

import java.util.TimerTask;

/**
 * @author John·Louis
 * @date created on 2020/3/18
 * description:
 */
public class TimerTaskService extends TimerTask {
    @Override
    public void run() {
        try {
            //要执行的任务逻辑写在这里
            System.out.println("timer 定时任务执行成功");
        } catch (Exception e) {
            System.out.println("timer 定时任务执行");
        }
    }
}
