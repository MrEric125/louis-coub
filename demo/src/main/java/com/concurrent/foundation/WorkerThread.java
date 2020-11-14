package com.concurrent.foundation;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 */
public class WorkerThread extends Thread {
    private final int requestCount;

    public WorkerThread(int id, int requestCount) {
        super("worker-" + id);
        this.requestCount = requestCount;

    }

    @Override
    public void run() {
        int i = requestCount;
        String requestID;
        RequestIDGenerator instance = RequestIDGenerator.getInstance();
        while (i-- > 0) {
            requestID=instance.nextID();

        }
    }

    private void processRequest(String requestId) {
        try {
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName()+"got requestID:" +requestId);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
