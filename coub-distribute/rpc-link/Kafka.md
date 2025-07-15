# producer

producer客户端流程 见图片 producer-client

整个生产者客户端由两个线程协调运行，这两个线程分别为主线程和Sender 线程（发送线
程）。在主线程中由K afkaProd ucer 创建消息，然后通过可能的拦截器、序列化器和分区器的作
用之后缓存到消息累加器（ RecordAccumulator ，也称为消息收集器〉中。Sender 线程负责从
RecordAccumulator 中获取消息并将其发送到Ka fka 中。

RecordAccumulator 主要用来缓存消息以便Sender 线程可以批量发送，进而减少网络传输
的资源消耗以提升性能。

重要的生产者参数

1. acks:
2. max.request.size
3. retries 和retry.backoff.ms
4. compression.type
5. connections.max.idle.ms
6. linger.ms
7. receive.buffer.bytes
8. send.buffer.bytes
9. request.timeout.ms

一个正常的生产者逻辑是：
(1 ）配置生产者客户端参数及创建相应的生产者实例。
( 2 ）构建待发送的消息。
(3 ）发送消息。
(4 ）关闭生产者实例。

=====

# consumer

点对点模式和pub/sub模式都是由消费组这个概念来完成
原则： 每个分区只能被一个消费组中的一个消费者消费，但是不同消费组中的消费者可以消费到同一个partition
中的数据

如果所有的消费者都隶属于同一个消费组，那么所有的消息都会被均衡地投递给每一
个消费者，即每条消息只会被一个消费者处理，这就相当于点对点模式的应用。
如果所有的消费者都隶属于不同的消费组，那么所有的消息都会被广播给所有的消费
者，即每条消息会被所有的消费者处理，这就相当于发布／订阅模式的应用。

消费组是一个逻辑上的概念，它将旗下的消费者归为一类，每一个消费者只隶属于一个消
费组。每一个消费组都会有一个固定的名称，消费者在进行消费前需要指定其所属消费组的名
称，这个可以通过消费者客户端参数group.id 来配置，默认值为空宇符串。

重要参数

1. bootstrap.servers
2. group.id
3. key.deserializer value.deserializer

一个正常的消费者逻辑
(1 ）配置消费者客户端参数及创建相应的消费者实例。
( 2 ）订阅主题。
( 3 ）拉取消息并消费。
(4 ）提交消费位移。
( 5 ）关闭消费者实例。
