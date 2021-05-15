package com.concurrent;

import java.util.concurrent.TimeUnit;

public class StopThread {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!getStopRequested()) {
                i++;
            }
            //35195769
            //34811578
            //34889351
            System.out.println(i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
//        stopRequested = true;
        setStopRequested(true);
    }

    private static synchronized void setStopRequested(boolean b){
        stopRequested = b;
    }
    private static synchronized boolean getStopRequested() {
        return stopRequested;
    }
}
