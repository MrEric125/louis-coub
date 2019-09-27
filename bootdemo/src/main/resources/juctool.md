明白一点，我们使用这些工具类不一定就能够保证我们的程序线程安全，所以要合理正确的使用时非常的必要的


# CyclicBarrier
**CyclicBarrier主要是用来解决几个会相互协作的线程之间状态不一致的问题**
它被称之为cyclic说明它是可以被重入的


 两个构造方法
 CyclicBarrier(int parties, Runnable barrierAction)
 CyclicBarrier(int parties)
 核心方法：：
 
 await() 或者
 await(long timeout, TimeUnit unit)
当线程到达了await就会标记一下，直到有parties个线程到达了await点，就会唤醒所有线程，执行相应逻辑，
如果当前线程是最后一个到达栅栏点的，那么当先线程会先于其他线程执行，happen-before原则

如果线程在等待的过程中抛出异常，那么其他等待的线程也会抛出`BrokenBarrierException`异常，

await() 方法返回的是当前到达栅栏点的线程索引，索引下标是从0开始的，所依如果当前线程是`getParties-1`的话，

那么就会唤醒其他等待的线程

CyclicBarrier中的属性包括
```
    //执行逻辑中使用的可重入锁，一个CyclicBarrier实例只是用一个锁
    private final ReentrantLock lock = new ReentrantLock();
    //线程状态
    private final Condition trip = lock.newCondition();
    总共会有多少个参与的线程
    private final int parties;
    /* 当线程被唤醒的时候，当前执行的线程 */
    private final Runnable barrierCommand;
    /** 通过内部类，维护了一个"代"的概念，每个栅栏点都代表了一个generation实例
    *当么个barrier点被重置，或者被唤醒的时候，那么这个"代"的状态就会改变
    */
    private Generation generation = new Generation();

    //还有多少个线程处于等待状态
    private int count;
```


# semaphore
# countDownLatch
# Executors
# Exchanger