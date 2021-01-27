## java 异步调用Future知多少？

在某些时候，我们希望一个线程执行完某个任务之后，能够给我们返回某些我们想要的数据，在Future中就能实现这样的一个功能，触发哪些潜在不叫好事的操作，把调用线程给解放出来，，让线程能够继续执行有价值的工作，Future使用的范围还是很广的无论是Stream包，还是JUC，都有很多类实现了Future，


Future的使用其实也很简单，看看一下的接口，

```java 
public interface Future<V> {

    /**
     * 尝试去取消这个执行的线程任务，但是如果这个执行任务已经结束了，那么取消就会失败
     * 后期如果直接get,程序就会抛出CancellationException
     * 
     */
    boolean cancel(boolean mayInterruptIfRunning);

    /**
     * 当任务在在正常完成之前如果执行了cancel(),那么就返回true,否则返回false
     */
    boolean isCancelled();

    /**
     * 查看当前任务是否已完成，如果当前任务已经取消，也是会返回已完成
     */
    boolean isDone();

    /**
     *等待线程执行完成之后获取我们需要的结果
     */
    V get() throws InterruptedException, ExecutionException;

    /**
     * 一般我们不建议使用上面方式，如果这个计算的线程一直阻塞住了，我们不可能一直等待，
     * 这个方法指定一个等待的时间，那如果超时还没有获取到结果就会抛出一个TimeoutException异* 常
     */
    V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}

```

这些接口既能取消一个线程的执行，也能查看线程是否已经被取消等等


在项目中使用Future更常见的也就是FutureTask, ExecutorService中的submit就是创建的FutureTask,我们先来看一个自己实现的FutureTask小例子

```java

    public  void test1() {
        Callable<String > callable = () -> {
            Thread.sleep(2000);
            return "TEST";
        };
        FutureTask<String> future = new FutureTask<>(callable);
        try {
            // 把创建的FutureTask交给另外一个线程执行，但是如果我们直接future.run(),那么这个程序就相当于是同步到，不是异步的
            Thread thread = new Thread(future);
            thread.start();
            //如果咱们
            future.run();
            doSomething();
            //一般项目中会用future.get( timeout, unit)这个重载方法
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

```

我们再来看看线程池执行submit方法，真正的执行是在`AbstractExecutorService`中的submit就是通过Runable或者Callable创建一个FutureTask()，

然后再把FutureTask交给execute()方法，最后返回的就是一个Future,这样一来就相当于让我们程序正在执行的过程中，创建一个新的线程执行FutureTask(),达到异步的效果

源码的代码如下

```java
public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> ftask = newTaskFor(task);
        execute(ftask);
        return ftask;
    }

    
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable);
    }

```


FutureTask有三个重载构造器，就是要把Callable或者Runnable丢进去，FutureTask自己实现了了Runnable的run方法，




futureTask维护了线程的这样几种状态，线程中的几种状态之间的相互转换是一下的关系
  * Possible state transitions:
     * NEW -> COMPLETING -> NORMAL
     * NEW -> COMPLETING -> EXCEPTIONAL
     * NEW -> CANCELLED
     * NEW -> INTERRUPTING -> INTERRUPTED
我们可以想象`FutureTask`实现的是`Runnable`,和`Future`,那么它是如何做到异步和返回值的呢？ `Runnable`中的`run()`可是没有返回值的.

重点就在于`FutureTask`中的成员变量有一个callable,如果我们在构造FutureTask的时候，传入的是Runnable，那么就通过RunnableAdapter将

Runnable转换成成员变量callable,到时候就可以在FutureTask实现的run()方法中统一执行,这就是FutureTask中的整体概括了

```java
public class FutureTask<V> implements RunnableFuture<V> {

    //线程的状态，就是前面提到的几种状态，几种状态之间也可以相互转化
    private volatile int state;
   
   
   //线程调用的底层执行者
    private Callable<V> callable;
    // FutureTask
    private Object outcome; 
     // 真正执行callable的线程
    private volatile Thread runner;
//    正在等待执行线程的栈
    private volatile WaitNode waiters;

    public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = NEW;      
    }


    public void run() {
        // 判断线程的状态，
        if (state != NEW ||
        // CAS执行
            !UNSAFE.compareAndSwapObject(this, runnerOffset,
                                         null, Thread.currentThread()))
            return;
        try {
            Callable<V> c = callable;
            if (c != null && state == NEW) {
                V result;
                boolean ran;
                try {
                    // 返回执行结果
                    result = c.call();
                    ran = true;
                } catch (Throwable ex) {
                    result = null;
                    ran = false;
                    setException(ex);
                }
                if (ran)
                // 设置结果到Object
                    set(result);
            }
        } finally {
            runner = null;
            int s = state;
            if (s >= INTERRUPTING)
                handlePossibleCancellationInterrupt(s);
        }
    }
}
```

但即便Future能够异步执行，他也不是万能的，以下这些场景，FutureTask可能很难完成


但是Future也有局限性比如这样几种情况：
 - 将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第
    一个的结果
 - 等待Future集合中的所有任务都完成。 
 - 仅等待Future集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同
    一个值），并返回它的结果。   
 - 通过编程方式完成一个Future任务的执行（即以手工设定异步操作结果的方式）。

 为了解决这些Future难以处理上述这几个问题，java 1.8中就引入了一个功能比较强的接口`CompletionStage`，它针对单个线程或者多个线程有很多的操作，


 能够很好的与函数式编程相结合起来。`CompletionStage`只有一个实现类`CompletableFuture`,`CompletableFuture`结合了CompletionStage的功能和`Future`的功能，

>`CompletionStage`代表异步计算的一个阶段或者是一个步骤，每个方法执行完之后都返回一个`CompletionStage`，这样我们就能够构成一个调用链

```java
//将正常的同步调用转化成异步调用
public static Future<String> test2() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            String calculate = calculateSomething();
            
            future.complete(calculate);
        }).start();
        // 还不等程序执行完成就直接返回了
        return future;
}

// 假设一个非常耗时的操作
public static String  calculateSomething() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "TTTTT";

    }

```    

至于详细的`ComletableFuture`的使用，以及执行过程，留在下一章中讲解，并且讲解的点主要就是围绕着以及FutureTask无法完成的几点来讲解

