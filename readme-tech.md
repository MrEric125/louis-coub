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

   ​	相关文章： https://www.cnblogs.com/tgycoder/p/5410057.html

   1. **索引实现**

      1. B-Tree

         **B-Tree中重要的概念**：

         d（B-Tree的度）：度会对约束节点的key和指针的数量，每个非叶子结点由n-1个key和n个指针组成，其中d<=n<=2d；每个叶子结点至少包含一个key和两个指针，最多包含2d-1个key和2d个指针，叶结点的指针均为NULL；

         h（B-Tree的高）：所有叶结点都在同一层，深度等于树高h；

         **B-tree需要满足的条件**：
   
         为了描述B-Tree，首先定义一条数据记录为一个二元组[key, data]，key为记录的键值，对于不同数据记录，key是互不相同的；data为数据记录除key外的数据。那么B-Tree是满足下列条件的数据结构：
         -  d>=2，即B-Tree的度；
         -  h为B-Tree的高；
         -  每个非叶子结点由n-1个key和n个指针组成，其中d<=n<=2d；
         -  每个叶子结点至少包含一个key和两个指针，最多包含2d-1个key和2d个指针，叶结点的指针均为NULL；
         -  所有叶结点都在同一层，深度等于树高h；
         -  key和指针相互间隔，结点两端是指针；
         -  一个结点中的key从左至右非递减排列；
         -  如果某个指针在结点node最左边且不为null，则其指向结点的所有key小于v(key1)，其中v(key1)为node的第一个key的值。
         -  如果某个指针在结点node最右边且不为null，则其指向结点的所有key大于v(keym)，其中v(keym)为node的最后一个key的值。
         -  如果某个指针在结点node的左右相邻key分别是keyi和keyi+1且不为null，则其指向结点的所有key小于v(keyi+1)且大于v(keyi)。
   
      
   
   2. B+Tree（多叉平衡树）
   非叶子节点不存储data,只存储索引（冗余），可以放更多的索引
   叶子节点包含所有的索引字段，
   叶子结点用指针连接，提高区间访问的性能
   `shwo gllobal status like 'innodb_page_size'`: 一个page节点默认大小是16kb
   为什么要设置成16kb?
   将data放到叶子节点，那么非叶子节点中存储的数据可以非常大了.
   想想一个bigint(主键) 是`8byte`，一个指向子节点的内存占用是`6byte`
   也就是说一个索引需要`14b`,所以每个节点能够存放`16kb/14byte=1170`个索引
   假设一个叶子结点的索引和data占用内存为`1kb`,一个B+Tree如果被撑满了，那么能存放的数据为：
   `1170*1170*（16/1)=2190万`
   **InnoDb索引实现（聚簇索引）**
   表数据文件本身就是按照B+Tree组织爱的一个索引结构文件
   聚簇索引-叶节点包含了完整的数据记录
   为什么InnoDb表必须有主键，并且土建使用整型的自增主键
   为什么非主键索引结构叶子结点存储的是主键值（一致性和节省存储空间）
   
   
   
3. **聚簇索引**

      ​	聚簇索引并不是一种单独的索引类型，而是一种数据存储方式，具体细节依赖其实现方式，InnoDB的聚簇索引实际上是在同一个结构中保存了B-Tree索引和数据行。当表有聚簇索引时，它的数据行实际上存放在索引的叶子页（leaf page）中，因为无法同事吧数据行存放在两个不同的地方，所以表中只能有一个聚簇索引（不过覆盖索引可以模拟多个聚簇索引的情况）。InnoDB中默认是通过`主键聚集数据`的，如果没有定义主键，InnoDB会选择一个非空索引代替，如果没有这样的索引，InnoDB会隐式定义一个主键来聚簇索引，

!

1. question:
    索引数据结构
    索引是如何支撑千万级表的快速查询
    如何基于索引B+树精准建立高性能索引
    联合索引底层数据结构又是什么样子的
    mysql 索引优化最佳实践
    
    

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

   两个concurrentHashMap,一个用于存储全类名，一个用于存储实例，Spring中的单例模式就是通过这种方式来实现的，

2. aop 

3. transaction

4. web

#### java8 java11相关新特性


二叉树形式：
平衡二叉树
红黑树
hash表