### pattern

### issue
1. mysql 的数据交互方式是以什么方式进行的，
2. 2020-02-01 11:07:56.386  WARN 7380 --- [eCheck-thread-1] o.a.d.r.e.s.header.ReconnectTimerTask    :  [DUBBO] Reconnect to channel HeaderExchangeClient [channel=org.apache.dubbo.remoting.transport.netty4.NettyClient [/10.0.75.1:6873 -> /10.0.75.1:20880]], because heartbeat read idle time out: 180000ms, dubbo version: 2.7.3, current host: 10.0.75.1


#### 原理问题
dubbo原理，kafka高可用原理，Elasticsearch分佈式架構原理，redis单线程原理，Dubbo工作原理

----

dubbo 调用接口的流程，工作的原理。
网络协议都有哪些，序列化协议都有哪些？
RPC网络协议都有哪些，RPC序列化方式都有哪些。
dubbo中都有哪些网络协议，都有哪些RPC序列化方式

docker 中如何制定自定义ip

dubbo
服务治理(调用链路的自动生成，各种服务之间的协调)（可以简单讲讲服务治理包括哪些方面，也可以说自己公司里面没有太多精力做服务治理这一块）
服务降级（如果服务调用挂了，为了不对其它服务产生不必要的影响，调用一个备用的逻辑）
失败重试，降级重试。
服务鉴权(各个服务模块之间的鉴权功能的实现，保证某个服务只能被另外某些服务调用)
分布式幂等性的问题，以及解决方案，

#### 生产问题
生产中遇见的各种问题
接口被访问的频率，以及响应时效



### plan
源码查看计划
1. spring(作为IOC和AOP的基础)
2. spring boot(作为服务框架的典型)
3. netty(作为高并发服务器的典型)
4. dubbo(作为RPC的典型，)
