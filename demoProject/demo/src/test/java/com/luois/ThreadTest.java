package com.luois;

/**
 * @author John·Louis
 * @date created on 2020/2/1
 * description:
 */
public class ThreadTest {

    public static void main(String[] args) {
        new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            String parentGroupName = currentThread.getThreadGroup().getParent().getName();
            System.out.println("parent group name: " + parentGroupName);
            System.out.println("current group name: " + currentThread.getThreadGroup().getName());
            System.out.println("thread states: " + currentThread.getState());

        }).start();

    }
}
