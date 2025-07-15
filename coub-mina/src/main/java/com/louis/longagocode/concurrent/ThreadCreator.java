package com.louis.longagocode.concurrent;

public class ThreadCreator {


    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                synchronized (resourceA) {
                    System.out.println("thread A get resource A lock");

                    synchronized (resourceB) {
                        System.out.println("thread A get resource B lock");

                        System.out.println("thread Arelease resource A lock");

                        resourceA.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (resourceA) {
                    System.out.println("thread B get resource A lock");

                    System.out.println("thread B try get resourceB lock..");

                    synchronized (resourceB) {
                        System.out.println("thread B get resource B lock");

                        System.out.println("thread B release resource A lock");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();


}
