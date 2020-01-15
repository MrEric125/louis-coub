#### 线程池

首先我们来看看通过Executors来创建的线程池，返回的结果都是ExcutorServer接口

创建方式| 功能|参数
:-|:-:|:-:
newFixedThreadPool(int)|创建固定大小的线程池|线程池中线程的个数
newWorkStealingPool(int)||
newWorkStealingPool()||
newSingleThreadExecutor()|创建只有一个线程的线程池|
newCachedThreadPool()|创建一个不限线程数上限的线程池，任何提交的任务都将立即执行|
newScheduledThreadPool(int)|主要用来在给定的延迟后运行任务，或者定期执行任务|

以上所有的方式都是调用ThreadPoolExecutor的重载构造方法，最后所有的重载方法都会调用这一个构造方法

```java
/**
 
     * @param corePoolSize 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
     * @param maximumPoolSize 线程数的上限
     * @param keepAliveTime 超过corePoolSize的线程的idle时长，
     * @param unit  
     * @param workQueue  任务的排队队列
     * @param threadFactory  产生新线程的方式
     * @param handler 拒绝执行的策略

     */
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        ........省略
    }
```

我们看到这里有一个BlockingQueue,但是在Executors中所有的newXXXThreadPoor()中创建的BlockQueue都是无界队列，就可能出现OOM异常。

通过以上的这种方式算是吧线程池给创建起来了
此处插入类图


在ExecutorService中定义了这样几个方法，用于提交任务

```java
public interface ExecutorService extends Executor {



     /**
     * 如果参数是Callable，那么执行完之后可能会有返回结果，
     * 但是如果是Runable,那么Future.get()就会为空
     *
     */
    <T> Future<T> submit(Callable<T> task);

    <T> Future<T> submit(Runnable task, T result);

    Future<?> submit(Runnable task);

    // 省略其它方法

}

```
##### 各个线程池的功能：

1. newFixedThreadPool
    固定线程数，无界队列，适用于任务数量不均匀的场景，对内存压力不敏感，单系统负载比较敏感的场景
2. newCachedThreadPool
    无限线程数，适用于要求低延迟的短期任务场景
3. newSingleThreadExecutor
    单个线程的固定线程池，适用于保证异步执行顺序的场景
4. newScheduledThreadPool
    适用于定期执行任务的场景，支持固定平路和固定的延时
5. newWorkStralingPool
    使用ForkJoinPool，多任务队列的固定并行度。适合任务执行时长不均匀的场景

##### 注意事项

**在《阿里巴巴 Java 开发手册》“并发处理”这一章节，明确指出线程资源必须通过线程池提供，不允许在应用中自行显示创建线程。**

> **使用线程池的好处是减少在创建和销毁线程上所消耗的时间以及系统资源开销，解决资源不足的问题。如果不使用线程池，有可能会造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。**

通过Executors创建的线程池也是会有一定弊端的，

1. FixedThreadPool和SingleThreadPool ，允许请求的队列长度是`Integer.MAX_VALUE`,这个可能会堆积大量的请求，导致OOM

2. CachedThreadPool 和 ScheduledThreadPool : 允许的创建线程数量为 `Integer.MAX_VALUE` ，可能会创建大量的线程，从而导致 OOM 。

所依我们推荐使用**ThreadPoolExecutor**的构造方法手动创建线程池

https://www.cnblogs.com/CarpenterLee/p/9558026.html









