#### Hystrix
>通常我们在进行远程服务调用的时候，为了让某些服务发生了错误或者表现不佳的时候，能够不影响到另外的一些服务，引入了一个所谓的
"所谓的客户端弹性模式"，这个模式的目的就是为了让客户端能够快速的失败，而不消耗诸如线程池或者数据库之类的资源，，并且可以
防止远程服务的问题在服务与服务之间传播，导致批量服务挂掉

**客户端弹性模式有这样几种**:
    
-  客户端负载均衡(client load balance)：   
将服务端的服务实例(集群)的物理位置物理位置缓存在客户端，当服务消费者需要调用该服务的时候，负载均衡器就会从它维护的服务实例（集群）中返回一个给客户端调用，这种模式就是Ribbon提供的功能，其它三种都是Hystrix提供的功能
-  断路器模式(circuit breaker):     
确保服务客户端不会重复调用失败的服务
-  后备模式( fallback)：    
当服务调用失败的时候，后备模式询问是否有可执行的方案
-  舱壁模式(bulkhead)：     
隔离服务客户端上不同的服务调用，以确保表现不佳的服务不会耗尽客户端所有资源

后面三种客户弹性模式都是Hystrix能够实现的

首先说说自己的环境，使用的是spring-boot 和spring -cloud-hystrix

```xml
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>

```
如果我们想让Hystrix生效，需要将@EnableCircuitBreaker或者@EnableHystrix 加入到引导类上，使Hystrix能够生效

##### 断路器模式
  其实断路器模式超级简单，原理就是 当我们调用服务超过指定时间，那么就将该服务断开，避免异常会在其他服务之间蔓延，导致其他服务不可用  

  简单测试代码如下

```java 
    @GetMapping ("/circuit")
    @HystrixCommand
    public String circuit(@RequestParam int num,@RequestParam long sleepTime) {
        // 这个工具类，就是让想成sleep指定的的时间，然后返回一个RandomNum
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);
        return runLong + "";
    }


public class TimeOutUtils {


    public static int randomlyRunLong(int bound,long sleepTime) {
        Random random = new Random();
        int randomNum = random.nextInt((bound - 1) + 1) + 1;
        if (bound==randomNum) sleep(sleepTime);
        return randomNum;
    }

    private static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

>我们看到上面的@HystrixCommand虽然什么参数都没有加，但是当线程终端时间超过1000ms的时候就会抛出一个`com.netflix.hystrix.exception.HystrixRuntimeException`的异常，如果我们什么参数都不带，那么这个注解就会将我们所有的服务调用都放在同一个线程池下，这就可能会导致应用程序出现某些问题

如果我们在使用断路器的时候想要定制超时时间，我们只需要像这样加一个@HystrixProperties

```java 

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })
    @GetMapping ("/circuit")
    public String circuit(@RequestParam int num,@RequestParam long sleepTime) {
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);
        return runLong + "";

    }

```

@HystrixCommand中有些参数不配置都是会有默认值的，其中默认值是配置在`com.netflix.hystrix.HystrixCommandProperties`中的，有些配置，如果不熟悉可以查阅看看，

我们可以看到断路器模式相当于是资源调用者和资源提供者之间的一个中间人，当这个中间人发现有异常的时候就会抛出异常，但是有时候我们发现调用服务有异常的时候，会使用一个默认的服务，让页面能够有数据显示不至于挂掉，
就出现了后备模式


##### 后备处理模式

后备模式很简单的意见就是需要指定一个fallbackMethod方法，需要注意的是，这个fallbackMethod指定的后备方法必须得和当前@HystrixCommand注解的方法放在同一个类中，并且有相同的参数，否则就会跑出一个`com.netflix.hystrix.contrib.javanica.exception.FallbackDefinitionException: fallback method wasn't found: fallback([int, long])`异常，示例代码如下：

```java
    @GetMapping("fallback")
    @HystrixCommand(fallbackMethod = "fallback")
    public String hystrixFallback(@RequestParam int num,@RequestParam long sleepTime) {
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);

        return runLong + "";
    }

    public String fallback(int num, long sleepTime) {
        log.info("fallback");
        return num + sleepTime + "\n fallback";
    }
```



##### 舱壁模式

>前面说到，@HystrixCommand默认会将所有的服务调用都使用同一批线程来执行，那么这些线程是为了处理整个java预留的，所以如果出现大量请求的话，一个服务出现性能问题，也会导致java容器的所有线程被刷爆并等待线程处理工作，同时阻塞新的请求。这个时候就会该使用舱壁模式，舱壁模式会将所有的远程调用，隔离在自己的线程池中。

具体使用如下示例代码：
```java
@GetMapping("/bulkhead")
    @HystrixCommand(fallbackMethod = "fallback",threadPoolKey = "bulkhead",threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "30"),
            @HystrixProperty(name = "maxQueueSize",value = "10")
    })
    public String bulkhead(@RequestParam int num,@RequestParam long sleepTime) {
        int runLong = TimeOutUtils.randomlyRunLong(num, sleepTime);

        return runLong + "";
    }

    public String fallback(int num, long sleepTime) {
        log.info("fallback");
        return num + sleepTime + "\n fallback";
    }

```
##### 详细的配置说明看后面的参数配置表：

@HystrixCommand 参数详细配置说明

|属性名称|默认值|描述|
|:-:|:-:|:-:|
|fallbackMethod|None|标识类中的方法，如果远程调用超时，将调用该方法，回调方法必须与@HystrixCommand注解在一个类中，并且必须具有与调用类相同的方法签名，如果值不存在，Hystrix会抛出异常|
|threadPoolKey|None|给予@HystrixCommand一个唯一的标识名称，并创建一个独立于默认线程池的线程池，如果没有定义任何值，则将使用默认的线程池|
|threadPoolProperties|None|核心的Hystrix注解属性，用于配置线程池的行为|
|coreSize|10|设置线程池大小|
|maxQueueSize|-1|设置线程池前面的最大队列大小，如果设置为-1，则不适用队列，Hystrix将阻塞请求，直到有一个线程可用来处理，这个参数的配置只能在线程池首次初始化的时候设置|
|circuitBreaker.requestVolumeThreshold|20|设置Hystrix开始检查断路器是否掉渣之前滚动窗口中必须处理的最小请求量。此值只能使用commandPoolProperties属性来设置|
|circuitBreaker。errorThresholdPercentage|50|在断路器跳闸之前，滚动窗口内必须达到的故障比。此值只能使用commandPoolProperties属性来设置|
|circuitBreaker.sleepWindowInMilliseconds|5000|在断路器跳闸之后，尝试进行服务调用之前，将要等待的时间(以毫秒为单位)此值只能使用commandPoolProperties属性来设置|
|metricsRollingStats.timeInMilliseconds|10000|Hystrix收集和服务调用的统计信息的滚动窗口(以毫秒为单位)|
|metricsRollingStats.numBuckets|10|Hystrix在一个监控窗口中维护的度量桶的数量，监视窗口内的桶数量越多，Hystrix在窗口内监控故障的时间越低|
||||

完结