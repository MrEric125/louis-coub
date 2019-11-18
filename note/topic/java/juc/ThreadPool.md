
线程池一般都是通过Executors来创建的，返回的结果都是ExcutorServer接口

创建方式| 功能|参数
:-|:-:|:-:
newFixedThreadPool(int)|创建固定大小的线程池|线程池中线程的个数
newFixedThreadPool(int,ThreadFactory)|同上|线程池中线程的个数，创建线程池的工厂接口
newWorkStealingPool(int)||
newWorkStealingPool()||
newSingleThreadExecutor()|创建只有一个线程的线程池|
newSingleThreadExecutor(ThreadFactory)||
newCachedThreadPool()|创建一个不限线程数上限的线程池，任何提交的任务都将立即执行|
newCachedThreadPool(ThreadFactory)|通过ThreadFactory来创建|
newScheduledThreadPool(int)||
newScheduledThreadPool(int,ThreadFactory)||

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

#### 各个线程池的功能：
1. newFixedThreadPool
    固定线程数，无界队列，适用于任务数量不均匀的场景，对内存压力不敏感，单系统负载比较敏感的场景
2. newCachedThreadPool
    无线线程数，适用于要求低延迟的短期任务场景
3. newSingleThreadExecutor
    单个线程的固定线程池，适用于保证异步执行顺序的场景
4. newScheduledThreadPool
    适用于定期执行任务的场景，支持固定平路和固定的延时
5. newWorkStralingPool
    使用ForkJoinPool，多任务队列的固定并行度。适合任务执行时长不均匀的场景

```
https://www.cnblogs.com/CarpenterLee/p/9558026.html









