#AQS设计思想理解
AQS作为同步器，主要作用当然是为了同步功能而设计的，那么来看看正常的通过同步器来执行的逻辑：
   > 获取同步器的锁->执行逻辑-> 释放锁    
   
那么现在问题来了，在不适用内置的synchronize的前提下，如何获取同步锁，来达到只能当前线程执行，其他线程等待。
当执行逻辑执行完成之后，释放锁的时候，其他线程能够争夺资源。

我们会想到能不能通过线程的不同状态来维护同步器的原子状态，
将等待线程放入一个队列中，等到需要的时候就从队列中取出线程
- 线程阻塞和释放阻塞
- 队列管理
- 同步器的状态管理


模型
ReentrantLock

ReentrantLock lock=new ReentrantLock()
默认创建的是一个非公平锁，如要要使用公平锁可以在创建new ReentrantLock(true)
lock()执行逻辑：
    1.查看当前state是否为0,如果为0,那么久将其更新问1，设置当前线程为exclusive，
    2.这个时候其他线程过来了查看state不为0，就会执行acquire(1)逻辑
    3.在acquire(arg)逻辑中，首先是tryAcquere(arg)

```
public final void acquire(int arg) {
    // 这里的tryAcquire(arg)==true 如果是获取到了资源就直接结束了,执行相应逻辑
    if (!tryAcquire(arg) &&
        //在给定的队列中找资源，
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        //如果这个时候还没有找到资源，那么就直接将当前线程中断了
        selfInterrupt();
}

final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    //获取状态，
    int c = getState();
    //如果状态为0，说明当前线程争夺到了资源，
    if (c == 0) {
        //如果获取到了资源，那么就将其设置为exclusive的然后更新state,返回true
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    //todo 那么这一般是什么样的情况会发生state不为0但是exclusive有是当前线程呢？
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
//当前线程通过自旋的方式在给定的队列中获取资源，
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();

            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
//当前线程通过acquire没有找到资源，就将当前线程入队了，入队就是将当前线程放在队列的尾部
 private Node addWaiter(Node mode) {
    Node node = new Node(Thread.currentThread(), mode);
    // Try the fast path of enq; backup to full enq on failure
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    enq(node);
    return node;
}
```
