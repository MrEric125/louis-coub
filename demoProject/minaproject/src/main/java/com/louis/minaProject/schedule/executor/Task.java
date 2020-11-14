package com.louis.minaProject.schedule.executor;

/**
 * @author John·Louis
 * @date created on 2020/3/18
 * description:
 */
public class Task implements Runnable {

    private String taskName;


    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        System.out.println("执行任务： " + taskName);


    }
}
