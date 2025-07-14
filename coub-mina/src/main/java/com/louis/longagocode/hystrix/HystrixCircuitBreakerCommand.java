package com.louis.longagocode.hystrix;

import lombok.extern.slf4j.Slf4j;

/**
 * @author John·Louis
 * @date create in 2019/8/24
 * description:
 */
@Slf4j
public class HystrixCircuitBreakerCommand extends HystrixCommand<String > {

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    private long productId;



    public HystrixCircuitBreakerCommand(long productId) {
        super(setter());
        this.productId = productId;
    }

    private static Setter setter() {

//     配置全局唯一表示服务分组名称
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("stock");
//        配置全局唯一标识服务的名称
        HystrixCommandKey serviceKey = HystrixCommandKey.Factory.asKey("getStocke");

//        配置全局唯一标识线程池的名称
        HystrixThreadPoolKey hystrixThreadPoolKey = HystrixThreadPoolKey.Factory.asKey("stock-pool");

//        配置线程池参数
        HystrixThreadPoolProperties.Setter threadPoolPropertiesDefaults = HystrixThreadPoolProperties.Setter()
                .withCoreSize(10)
                .withKeepAliveTimeMinutes(5)
                .withMaxQueueSize(Integer.MAX_VALUE)
                .withQueueSizeRejectionThreshold(10000);

        HystrixCommandProperties.Setter isolationStrategy = HystrixCommandProperties.
                Setter()
                //        配置隔离策略,这里使用的是线程池的隔离
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
//                是否允许断路器工作
                .withCircuitBreakerEnabled(false)
//                表示有多少流量的时候才会触发断路器
                .withCircuitBreakerRequestVolumeThreshold(50)
//                表示异常比例达到了多少的时候才会触发断路器 默认是50%
                .withCircuitBreakerErrorThresholdPercentage(40)
//                表示在断路器开启之后，多长时间内所有经过断路器的请求，都会被断开，不调用后台服务，直接走fallback降级机制
//                当改时间过了，那么断路器会尝试让一条请求通过，如果请求能够调用成功，那么就会关闭断路器 ，默认时间设置的是5s
                .withCircuitBreakerSleepWindowInMilliseconds(4000);





        return HystrixCommand.Setter.withGroupKey(groupKey)
                .andCommandKey(serviceKey)
                .andThreadPoolKey(hystrixThreadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolPropertiesDefaults)
                .andCommandPropertiesDefaults(isolationStrategy);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("===========================================");
        log.info("模拟断路器降级调用:productId:{}", productId);
        if (productId<20) {
            throw new Exception("调用服务出错");
        }
//        String url = "http://localhost:808/circuit?productId=" + productId;
        return " invoke 调用成功 \n";
    }

    @Override
    protected String getFallback() {
        return "fallback 获取降级信息 \n";
    }
}
