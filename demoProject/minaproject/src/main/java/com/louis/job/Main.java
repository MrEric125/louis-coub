package com.louis.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


@Slf4j
public class Main {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();

        defaultScheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myjob", "group").build();

        CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();

        defaultScheduler.scheduleJob(jobDetail, trigger);

        Thread.sleep(2000L);

        defaultScheduler.shutdown(true);

        log.debug("调度器10秒后停止，shutdown入参：{}，让任务调度完", true);




    }
}
