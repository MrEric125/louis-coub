package com.louis.longagocode.concurrent.foundation;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 */
public class VisibilityDemo {

    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(new TimeConsumingTask());
        thread.start();

        Thread.sleep(10000);
        timeConsumingTask.cancel();

    }
}
