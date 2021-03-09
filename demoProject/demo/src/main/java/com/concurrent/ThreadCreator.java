package com.concurrent;

public class ThreadCreator {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread test");

        });
        thread.start();

    }

}
