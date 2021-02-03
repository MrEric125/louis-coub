package com.louis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jun.liu
 * @date created on 2020/11/17
 * description:
 */
public class TreeNode {

    Lock lock = new ReentrantLock();

    public void method(){
        lock.unlock();

    }


}
