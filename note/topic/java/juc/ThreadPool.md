#### 线程池

首先我们来看看通过Executors来创建的线程池，返回的结果都是ExecutorService接口

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

----



**线程池相关问题**

1. 创建线程池的参数有哪些，分别代表什么
2. 为什么阿里要求不能直接使用Execcutors工具类创建线程池？
3. 线程池线程的数量如何配置？
4. 一般线程池提交任务，执行任务的过程？
5. 线程池中的ctl属性的作用是什么？
6. 县春城池的状态的有哪些？在什么时候下会出现？
7. 一般线程池中又哪些未实现的空方法，可以用作线程池的扩展？
8. 线程池中每一个具体的worker线程什么时候开始执行，？执行的过程是什么？
9. 核心线程与非核心线程在线程池中是怎么区分的？
10. 线程池中的那些方法可以提前创建核心线程？
11. 什么情况下worker线程会退出？
12. 核心线程成会不会退出？
13. 由于程序异常导致的退出和线程池内部机制导致的退出有什么区别
14. 线程池shutdown 与shutdownNow有什么区别？

----

首先在开发的过程中，为什么需要线程池呢？给我们带来了那些好处

- 提高系统的响应速度
- 如果每次多线程操作都创建一个线程，会浪费时间和消耗系统资源，而线程池可以减少这些操作
- 可以对多个线程进行统一管理，统一调度，提高线程池的可管理性

#### 创建线程池的参数有哪些？

线程池是怎么创建的呢？一个是使用Executors，另外就是手动创建线程池，要了解其每个参数的含义。Executors创建线程池的话，要不就是对线程的数量没有控制，如CachedThreadPool，要不就是是无界队列，如FixedThreadPool。**对线程池数量和队列大小没有限制的话，容易导致OOM异常。**所以我们要自己手动创建线程池：

- corePoolSize：核心线程数量，默认情况下每提交一个任务就会创建一个核心线程，直到核心线程的数量等于corePoolSize就不再创建。**线程池提供了两个方法可以提前创建核心线程，`prestartAllCoreThreads()`提前创建所有的核心线程，`prestartCoreThread`，提前创建一个核心线程**

- maximumPoolSize：线程池允许创建的最大线程数。只有当线程池队列满的时候才会创建

- keepAliveTime：线程池空闲状态可以等待的时间，默认对非核心线程生效，但是设置`allowCoreThreadTimeOut`的话对核心线程也生效

- unit: 保活时间的单位，创建线程池的时候，`keepAliveTime = unit.toNanos(keepAliveTime)`

- workQueue: 任务队列，用于保持或等待执行的任务阻塞队列。BlockingQueue的实现类即可，有无界队列和有界队列

  - ArrayBlockingQueue: 基于数组结构的有界队列，此队列按FIFO原则对元素进行排序
  - LinkedBlockingQueue: 基于链表的阻塞队列，FIFO原则，吞吐量通常高于ArrayBlockingQueue.
  - SynchronousQueue: 不存储元素的阻塞队列。每个插入必须要等到另一个线程调用移除操作。
  - PriorityBlockingQueue: 具有优先级的无阻塞队列

- threadFactory： 用于设置创建线程的工厂。

- handler：拒绝策略，当队列线程池都满了，必须采用一种策略来处理还要提交的任务。

  在实际应用中，我们可以将信息记录到日志，来分析系统的负载和任务丢失情况

  JDK中提供了4中策略：

  - AbortPolicy: 直接抛出异常
  - CallerRunsPolicy: 只用调用者所在的线程来运行任务
  - DiscardOldestPolicy： 丢弃队列中最老的一个人任务，并执行当前任务。
  - DiscardPolicy: 直接丢弃新进来的任务

#### 线程池提交任务的过程？

可以使用两个方法执行任务：

- execute() 提交不需要返回值的任务，无法判断是否执行成功，具体步骤上面我们有分析
- submit() 提交有返回值的任务，该方法返回一个future的对象，通过future对象可以判断任务是否执行成功。future的get方法会阻塞当前线程直到任务完成。
  - submit内部使用RunnableFuture对任务进行封装

整体分为三个步骤：

1. 判断当前线程数是否小于corePoolSize，如果小于，则新建核心线程，不管核心线程是否处于空闲状态
2. 核心线程创建满之后，后续的任务添加到workQueue中
3. 如果workQueue满了，则开始创建非核心线程直到线程的总数为maximumPoolSize
4. 当非核心线程数也满了，队列也满了的时候，执行拒绝策略

中间会有一些对当前线程池的检查操作。



![image-20200212084315747.png](https://user-gold-cdn.xitu.io/2020/2/12/170371a84d23c522?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



#### 线程池数量如何配置？

- 任务性质：CPU密集，IO密集，和混合密集
- 任务执行时间：长，中，低
- 任务优先级：高，中，低
- 任务的依赖性：是否依赖其它资源，如数据库连接

在代码中可以通过：`Runtime.getRuntime().availableProcessors();`获取CPU数量。线程数计算公式：

```
N = CPU数量
U = 目标CPU使用率，  0 <= U <= 1
W/C = 等待(wait)时间与计算(compute)时间的比率

线程池数量 =  N * U * (1 + W/C)
复制代码
```

不过最简单的线程数指定方式，不需要公式的话：

- CPU密集型，创建线程数为`CPU核数 + 1`
- IO密集型，线程数最好为`CPU核数 * n`，耗时越久，分配线程数多一些

#### 线程池的状态有哪些？

线程池的状态主要通过ctl属性来控制，通过ctl可以计算出：

- 当前线程池状态
- 当前线程的数量

计算规则主要是利用了按位操作：

```
11100000000000000000000000000000   RUNNING
00000000000000000000000000000000   SHUTDOWN
00100000000000000000000000000000   STOP
01000000000000000000000000000000   TYDYING
01100000000000000000000000000000   TERMINATED


11100000000000000000000000000000   ctl初始值
11100000000000000000000000000000  ~CAPACITY  
private static int runStateOf(int c)     { return c & ~CAPACITY; }

11100000000000000000000000000000   ctl初始值
00011111111111111111111111111111  CAPACITY
private static int workerCountOf(int c)  { return c & CAPACITY; }
    
private static int ctlOf(int rs, int wc) { return rs | wc; }  
复制代码
```

- RUNNING：运行状态，接受新任务，持续处理任务队列里的任务。
- SHUTDOWN：调用shutdown()方法会进入此状态，不再接受新任务，但要处理任务队列里的任务
- STOP：调用shutdownNow()方法，不再接受新任务，不再处理任务队列里的任务，中断正在进行中的任务
- TIDYING：表示线程池正在停止运作，中止所有任务，销毁所有工作线程。
- TERMINATED：表示线程池已停止运作，所有工作线程已被销毁，所有任务已被清空或执行完毕

```
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
复制代码
```

关于TIDYING和TERMINATED主要有一块代码区，可以看出来TIDYING状态紧接着就是TERMINATED。

```
						if (ctl.compareAndSet(c, ctlOf(TIDYING, 0))) {
                    try {
                    		// 默认是空方法
                        terminated();
                    } finally {
                        ctl.set(ctlOf(TERMINATED, 0));
                        termination.signalAll();
                    }
                    return;
                }
复制代码
```

#### 线程池提供的扩展方法有哪些？

默认有三个扩展方法，可以用来做一些线程池运行状态统计，监控：

```
 protected void beforeExecute(Thread t, Runnable r) { }  // task.run方法之前执行
 protected void afterExecute(Runnable r, Throwable t) { }  // task执行完之后，不管有没有异常都会执行
 protected void terminated() { }  
复制代码
```

默认线程池也提供了几个相关的可监控属性：

- taskCount: 线程池需要执行的任务数量
- completedTaskCount: 已经完成的任务数量
- largestPoolSize: 线程池中曾经创建的最大的线程数量
- getPoolSize: 线程池的线程数量
- getActiveCount: 活动的线程数

#### 线程池中的Worker线程执行的过程？

Worker类实现了Runnable方法，**在成功创建Worker线程后就会调用其start方法。**

```
w = new Worker(firstTask);
final Thread t = w.thread;   //理解为 w.thread = new Thread(w)
if (workerAdded) {
	t.start();
	workerStarted = true;
}


Worker(Runnable firstTask) {
	setState(-1); // inhibit interrupts until runWorker
	this.firstTask = firstTask;
	this.thread = getThreadFactory().newThread(this);
}
复制代码
```

Worker线程运行时执行runWorker方法，里面主要事情：

- 如果构造Worker的时候，指定了firstTask，那么首先执行firstTask，否则从队列中获取任务
- Worker线程会循环的getTask()，然后去执行任务
- 如果getTask()为空，那么worker线程就会退出
- 在任务执行前后，可以自定义扩展beforeExecute与afterExecute方法
- 如果检测到线程池为STOP状态，并且线程还没有被中断过的话，进行中断处理

简单来说就是不断的从任务队列中取任务，如果取不到，那么就退出当前的线程，取到任务就执行任务。

```
    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        // 代表着Worker是否因为用户的程序有问题导致的死亡
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                     (Thread.interrupted() &&
                      runStateAtLeast(ctl.get(), STOP))) &&
                    !wt.isInterrupted())
                    wt.interrupt();
                try {
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (Exception x) {
												  //... 不同的异常处理
                    } finally {
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(w, completedAbruptly);
        }
    }

复制代码
```

#### 线程池如何区分核心线程与非核心线程？

实际上内部在创建线程时，并没有给线程做标记，因此无法区分核心线程与非核心线程。可以看出`addWorker()方法`。

但是为什么可以保持核心线程一直不被销毁呢？

其内部主要根据当前线程的数量来处理。**也可以理解为，只要当前的worker线程数小于配置的corePoolSize，那么这些线程都是核心线程。线程池根据当前线程池的数量来判断要不要退出线程，而不是根据是否核心线程**

#### 核心线程能否被退出？

上面一个问题我们说到了内部其实不区分核心线程与非核心线程的，只是根据数量来判断是否退出线程，但是线程是如何退出的，又是如何一直处于保活状态呢？

**如果配置了allowCoreThreadTimeOut，代表核心线程在配置的keepAliveTime时间内没获取到任务，会执行退出操作。也就是尽管当前线程数量小于corePoolSize也会执行退出线程操作。**

workQueue.take()方法会一直阻塞当前的队列直到有任务的出现，因此如果执行的是take方法，那么当前的线程就不会退出。想要退出当前的线程，有几个条件：

- 1 当前的worker数量大于maximumPoolSize的worker数量。

- 2 线程池当前处于STOP状态，即shutdownNow

- 3 线程池处于SHUTDOWN状态，并且当前的队列为空

- 4 worker线程等待task超时了，并且当前的worker线程配置为可以被退出。

  ```
  timed=true
  ```

  - allowCoreThreadTimeOut配置为true
  - 线程数量大于核心线程数



![image-20200212092008155.png](https://user-gold-cdn.xitu.io/2020/2/12/170371a84da8a418?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



#### 如何提前创建核心线程数？

上面提到了，有两个方法：

- `prestartAllCoreThreads()`提前创建所有的核心线程
- `prestartCoreThread`，提前创建一个核心线程，如果当前线程数量大于corePoolSize，则不创建

#### 线程池异常退出与自动退出的区别？

如果线程是由于程序异常导致的退出，那么completedAbruptly为true，如下代码会再新建一个Worker线程。

如果线程是系统自动退出，即completedAbruptly为false的话，会根据配置判断当前可以允许的最小核心线程数量

- 配置allowCoreThreadTimeOut为true的话，最小核心线程数可以为0。
- 默认情况下最小线程数为corePoolSize

```
int c = ctl.get();
        if (runStateLessThan(c, STOP)) {
            if (!completedAbruptly) {
                int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
                if (min == 0 && ! workQueue.isEmpty())
                    min = 1;
                if (workerCountOf(c) >= min)
                    return; // replacement not needed
            }
            addWorker(null, false);
        }
复制代码
```

#### 线程池shutdown与shutdownNow有什么区别？

看代码主要三个区别：

- shutdown会把线程池的状态改为SHUTDOWN，而shutdownNow把当前线程池状态改为STOP
- shutdown只会中断所有空闲的线程，而shutdownNow会中断所有的线程。
- shutdown返回方法为空，会将当前任务队列中的所有任务执行完毕；而shutdownNow把任务队列中的所有任务都取出来返回。



![image-20200212093527721.png](https://user-gold-cdn.xitu.io/2020/2/12/170371a84e119a9f?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



### 最后

学无止境，还有很多细节，但足以打动面试官，觉得真是一个很用心的候选人呢... 希望这些能帮到你。



参考： https://juejin.im/post/5e435ac3f265da57537ea7ba?utm_source=gold_browser_extension

