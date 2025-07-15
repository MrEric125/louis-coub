package com.louis.longagocode.concurrent.locks;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author louis
 * <p>
 * Date: 2019/9/5
 * Description:
 */
@ThreadSafe
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);

    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
