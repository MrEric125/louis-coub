package com.concurrent.foundation;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 */
public class TimeConsumingTask implements Runnable {

    private volatile boolean toCancel = false;

    @Override
    public void run() {
        while (!toCancel) {
            if (doExecute()) {
                break;

            }
        }
        if (toCancel) {
            System.out.println("Task was canceled.");
        } else {
            System.out.println("task done");
        }

    }

    private boolean doExecute() {
        boolean isDone = false;
        System.out.println("executing.....");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isDone;

    }
    public void cancel() {
        toCancel = true;
        System.out.println(this+" caceled .");
    }

}
