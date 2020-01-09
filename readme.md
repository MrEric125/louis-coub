现在的情况，会用
docker
redis
elasticsearch(ELK)
kafka
mysql
java8
linux
netty
net
spring(spring boot ,spring cloud)
算法，数据结构，设计模式，这种基础知识还是需要很清楚的
vue

### 需要深入学习的知识

**docker**

网络，dockerFile docker-compose,容器编排技术

**linux**

bash 脚本，linux操作, ps netstat,grep tail 等常用的命令

**redis**
高性能的基石（单线程模型， epoll多路复用机制，集群模式，哨兵模式）
缓存容错机制，如何防止缓存穿透，缓存击穿，等问题
redis 如何实现分布式锁

**elasticsearch**
es高性能查询的基石lucense 倒排索引，
es数据查询传输方式，以及副本机制
es集群使用方法

**kafka**
高可用基本思路（有总结好的相关面试题，搞通透就可以）

**mysql**

 性能调优，索引相关，技术还需要继续深入

**java8**
除了常用的几种特性，Collectors中的静态方法还是需要搞熟练，以及使用Future异步编程

**spring**
可以开始看源码了，从这几个方面开始看
aop、ioc、事务
Spring boot 实现自动配置的方式，源码可以和 Spring源码配合一起看了

**算法**
重温几种常见的排序算法，最好能够白班编程将这几种算法写出来





















=================================

**核心**
	不论现在流行的spring cloud 全家桶微服务解决方案，还是dubbo 分布式系统解决方案，亦或是k8s容器编排技术，非常重要的就是网络传输。

​	我们有时候会说高性能，比方说redis单线程存储的高性能，elasticsearch查询的高性能，kafka异步消息队列的高性能，说的也是网络传输，

网络高速传输

RPC的几种方式：

| 框架                  | 支持语言 | 开源时间 | 公司     |
| --------------------- | -------- | -------- | -------- |
| dubbo                 | java     | 2011     | 阿里     |
| Motan                 | java     | 2016     | 微博     |
| Tars                  | C++      | 2017     | 腾讯     |
| Spring Cloud（Feign） | java     | 2014     | Pivotal  |
| gRPC                  | 多种语言 | 2015     | Google   |
| Thrift                | 多种语言 | 2007     | Facebook |
| ProtoBuf              | 多种语言 |          | Google   |



#### 知识点详解

##### docker

1. **docker网络：**
   1. 不同网络设置的作用
   2. 如何设置不同的网络形式
   3. 不同docker之间如何通信

2. **dockerFile**
   1. dockerFile中各种命令的使用以及含义
   2. docker 命令转dockerFile

3. **docker-compose**

   

#### mysql 

1. 锁实现细节
2. 索引
3. 调优

#### redis

1. 单线程模型
2. 秒杀系统
3. 缓存穿透，缓存击穿
4. 集群搭建

#### elasticsearch

1. 集群
2. 读写一致
3. bucket Aggregation
4. 脑裂问题

#### kafka

略

#### spring (源码)

1. ioc
2. aop 
3. transaction
4. web

#### java8 java11相关新特性

