## CompletableFuture：
CompletableFuture实现了两个接口，CompletableStage、Future,所依其功能就是能够异步执行的线程中间态，这其中的每个中间态都可以相互串联起来，达到最后完成一个任务的作用
1. 创建CompletableFuture对象
    创建有三种方式 
    一般执行CompletableFuture，不论是创建的(除了直接new以外)，还是中间操作都有三个重载方法，其中一个同步执行方法，两个异步执行方法，两个异步执行方法中有一个会指定Executor,那如果指定了Executor的话，那么就会用指定的线程池操作，否则就是用默认的ForkJoinPool中的线程来执行。
    在后面所有的代码摘取中一般只摘取其中一个
    - 通过new
    `CompletableFuture d = new CompletableFuture();`
    - 通过静态辅助方法
    用来返回一个已经计算好的值(计算好的值，是通过参数传入)，相当于是一个同步的方法，其实底层就是调用了`new CompletableFuture(U u)`
    ```java
    public static <U> CompletableFuture<U> completedFuture(U value)
    ```


    ```java
    
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);
    
     public static CompletableFuture<Void> runAsync(Runnable runnable);
    ```
    传入一个Supplier类型(格式如下：()-> reture U),最后返回一个值为U的CompletabeFuture,
    其实底层是通过Executor的execute(Runnable runnable),为什么这里又是Runnable呢？

    todo 这里可以贴一张类的关系图

    在CompletableFuture内部创建了一个静态内部类AsyncSupply, 它相当于是一个适配器，它实现了Runnable，并且内部聚合了一个CompletableFuture，在实现的run方法中将Supplier执行的结果交给了聚合的CompletableFuture的completeValue()方法，completeValue()的实现是通过CAS的方式实现的，所以它的操作是线程安全的，最后返回的CompletableFuture，其实就是AsynSupply中聚合的CompletableFuture

    runAsync(Runnable) 的执行流程就和supplyAsync的执行流程其实是一样的，CompletableFuture的内部封装了一个AsyncRun功能类似于AsyncSupply



中间操作
基本上这所有的中间操作都会传入一个action,这个action就是一个函数式接口，所以我们传入的参数只要满足相应的函数式接口即可
比如以下的
```java
 public CompletableFuture<T> whenComplete( BiConsumer<? super T, ? super Throwable> action) {

```

那么我们使用的时候传入的就是两个参数，参考如下，更多的使用需要参考java8函数式编程新特性
```java
 String join = CompletableFuture
                .supplyAsync(()->"method")
                .whenComplete((x, y) -> System.out.println(x.substring(1)))
                .join();

```

2. 计算
3. 获取元素
4. 类型：
        组合、转换、消费、完成时

主动结束操作，这个地方的结束操作和Stream中的终止操作不一样的是，函数式编程中的终止操作，就是来执行定义的Stream的,这里的结束操作是指同步的去获取CompletableFuture最后执行的结果
```java 
public T get();
public T get(long timeout, TimeUnit unit);
public T getNow(T valueIfAbsent) ;
public T join() 

```
异常处理：
