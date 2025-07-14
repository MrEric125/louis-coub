package com.louis.longagocode.concurrent.locks;

import com.concurrent.locks.ObjectLock;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.vm.VM;

/**
 * @author jun.liu
 * @since 2021/7/21 14:35
 */
public class InnerLockTest {

    @Test
    public void showBiasedLock() throws InterruptedException {
        System.out.println(VM.current().details());
        Thread.sleep(5000);
        ObjectLock objectLock = new ObjectLock();
        System.out.println("抢占锁前，lock的状态，，");

    }
}
