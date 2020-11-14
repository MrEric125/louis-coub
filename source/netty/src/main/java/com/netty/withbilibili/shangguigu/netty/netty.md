## java 原生的nio 的问题

nio的类库和api 的繁杂 使用麻烦
需要具备其它的额外技能，需要数据java多线程编程，nio编程，以及reactor模式 而且还必须对多线程和网络编程非常熟悉

需要我们处理客户端断连重连的问题，网络闪断开，半包读写，失败缓存，网络拥塞和异常流量的问题处理


netty主要的有点相比于nio  开发效率更高 性能更好

## 线程模型
传统阻塞IO线程模型  
* 问题：   
    一个链接创建后会创建一个线程，当并发量很大,的时候就会占用大量的资源;当如果没有数据可读的时候，就会阻塞在read那块，浪费资源    
* 解决办法  
    基于IO复用模型，多个连接公用一个阻塞对象，引用程序只需要在一个阻塞对象等待，无需阻塞等待所有连接，当某个连接有新的数据可以处理时，操作系统通知引用程序，线程从阻塞状态返回，
    开始进行业务处理    
    基于线程池复用线程资源，不必再为每个连接创建线程，将连接完成后的业务处理任务分配给线程进行处理，一个线程可以处理多个连接的业务

Reactor模型   
    通过一个或多个输入同事传递给服务器处理的基于事件处理，         
    服务器端程序处理传入的多个请求，并将它们同步分派到响应的处理线程中，使用IO复用监听事件
      
    

* 单Reactor 单线程  
   1.Select是前面IO（见包nio.chating.chatServer）复用模型介绍的标准网络编程API,可以实现引用程序通过一个阻塞对象监听多路连接请求     
   2.reactor对象通过Select监听客户端请求时间，收到时间后通过Dispatch 进行分发       
   3.如果是建立连接请求时间，则由acceptor通过Accept处理连接请求，然后创建一个Handler对象处理连接完成后续的业务处理  
   4.如果不是建立连接事件，则Reactor会分发调用链接对应的handler来响应    
   5.handler 会完成Read->业务处理->send的完整业务流程     
   **问题：**  
  还是所有线程都是同一个线程在处理，性能有限，如果handler 在处理业务的时候出现阻塞还是会有很大的问题的
  
* 单Reactor  多线程     
    1.Reactor对象通过select监控客户端请求事件，收到时间后，通过dispatch进行分发   
    2.如果建立连接请求，则由Acceptor通过accept处理连接请求，然后创建一个Handler对象处理完成连接后的各种事件     
    3.如果不是连接请求，则由Reactor分发调用连接对应的handler来处理，    
    4.handler只负责响应时间，不做具体的业务处理，通过read读取数据后，会分发给后面的worker线程池的某个线程处理业务，   
    5.worker线程池会分配独立的线程完成真正的业务，并返回给handler,在响应给client   
   优点：  
   可以充分的理由多和cpu的处理能力    
   缺点：  
   多线程数据共享和访问比较复杂，reactor处理所有的时间的监控也响应是在单线程上完成的
   
* 主从Reactor,多线程
    1.Reactor主线程MainReactor对象通过select监听连接事件，收到事件后，通过Acceptor处理连接事件      
    2.当Acceptor处理连接事件后，MainReactor将连接分配给SubReactor  
    3.subReactor将连接加入到连接队列进行监控，并创建handler进行各种事件处理   
    4.当有新实践发生时，subReactor就会调用对应的handler处理   
    5.handler通过read读取数据，分发给后面的worker线程处理    
    6.worker线程池分配独立的worker线程进行业务处理，并返回结构
    7.handler收到结果后就会通过send将结果返回给client
    8.Reactor多线程模型，主线程可以对应多个Reactor子线程，即MainReactor可以关联多个SubReactor
    
    

netty 线程模型是基于主从reactor 多线程模型进行改进
## netty线程模型
 主要是从Reactors多线程模型中做出了相应的改进，其中主从Reactor多线程模型有多个Reactor,

 1.BossGroup线程维护Selector,只关注Accept
 2.当接收到Accept时间，获取对应的SocketChannel，封装成NIOSocketChannel并注册到Worker线程时间循环中，并维护
 3.当worker线程坚挺到selector中通道发生自己感兴趣的事情后，就进行处理（由handler）

 工作原理：  
 1.netty抽象出两组线程池，bossGroup 专门负责接收客户端的连接，workergroup专门负责网络读写 
 2.bossGroup 和workergroup类型都是nioEventLoopGroup, 
 3.nioEventLoopGroup相当于一个时间循环组，这个组中含有多个时间循环，每个时间循环就是一个NIOEventLoop
 4.NIOEventLoop 表示一个不断循环的执行处理任务的线程，每个NioEventLoop都有一个selector,用于监听绑定在其上的socket的网络通信     
 5.NioEventLoopGroup 可以有多个线程，即可以含有多个NioEventLoop    
 6.每个BossNioEventLoop执行的步骤有三步    
  * 轮询accept事件    
  * 处理accept事件，与client建立连接，生成nioSocketChannel并将其注册到某个worker nioEventLoop上的selector
  * 处理人物队列中的任务 即runALlTask  
          

7.每个worker nioEventLoop循环执行的步骤
  * 轮询read,write事件
  * 处理io事件，即read,write 事件，在对应nioSocketChannel处理任务队列的任务， 即runAllTask

**任务队列中的Task有三种典型的使用场景**

1. 用户程序自定义的普通任务,提交到该channel 对应的NIoEventLoop 的`taskQueue`

   例：

   ```java
   @Override
   public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
       //提交到该channel 对应的NIoEventLoop 的`taskQueue`, 不会阻塞在这里
   	ctx.channel().eventLoop().execute(（）->{
          try{
   //模拟耗时的异步操作
              Thread.sleep(1000*10);
              ctx.writeAndFlush(Unpooled.copiedBuffer(msg))
          } catch(Exception e){
              e.printStact()
          }
       });
   }
   ```

2. 用户自定义定时任务,该任务提交到`scheduleQueue`

   例 

   ```java
   @Override
   public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
       //提交到该channel 对应的NIoEventLoop 的taskQueue, 不会阻塞在这里
   	ctx.channel().eventLoop().schedule(()->{
          try{
   //模拟耗时的异步操作
              Thread.sleep(1000*10);
              ctx.writeAndFlush(Unpooled.copiedBuffer(msg))
          } catch(Exception e){
              e.printStact()
          }
       },5,TimeUtils.SECOUNDS);
   }
   ```

   

3. 非当前Reactor,线程调用Channel的各种方法

   例如在推送系统的业务流程中，根据`用户的标识`，找到对应的`Channel引用`,然后调用Write类方法想该用户推送消息，就会进入掉这种场景中，最终的Write会提交到任务队列中后被`异步消费`



**Netty 模型**

1. netty 抽象出两组线程池，BossGroup  专门负责接收客户端连接，workerGroup专门负责网络读写操作
2. NioEventLoop 表示一个不断循环执行处理任务的线程，每个NioEventLoop都有一个Selector,用于监听绑定在其上的Socket网络通道，
3. NioEventLoop内部曹勇串行化设计，从消息的读取、解码、处理、编码、发送，适中由IO线程NioEventLoop负责，
   - NioEventLoopGroup下包含多个NioEventLoop
   - NioEventLoop中包含一个Selector,一个TaskQueue
   - NioEventLoop中的Selector上可以注册监听多个NIOChannel
   - 每个NioChannel只会绑定在唯一的NioEventLoop上
   - 每个NioChannel都绑定有一个自己的ChannelPipeline

**异步模型**： 返回的ChannelFuture 基于Future-Listener

​		Future-Listener: 当Future对象刚刚创建时，处于非完成状态，调用者可以通过返回的ChannelFuture来获取操作执行的状态，注册监听函数来执行完成之后的操作，常见方法（isDone(),isSuccess(),getCause() addListener()）

**工作原理:**  将一系列的Handler 放到Pipeline 中，pipeline相当于是一个责任链模式。

**netty 核心组件**
`bootstrap`,
`serverBootstrap`,
`Future`,
`ChannelFuture`(可以返回当前正在操作的通道),
`Channel`(执行网络IO操作的通道，可以获取网络连接的状态，不同协议，不同阻塞类型的连接都有不同的Channel类型与之对应)
    NIOSocketChannel: 异步的客户端TCPSocket连接
    NioServerSocketChannel:异步服务器端的TCPSocket连接
    NioDatagramChannel:异步的UDP连接
    NioSctpChannel:异步的客户端Sctp连接
    NioSctpServerChannel: 异步的Sctp服务器端连接，这些通道涵盖了UDP和TCP网络IO以及文件IO
`Selector`:netty通过Selector对象实现IO多路复用，
`ChannelHandler`:
`pipeline`
`ChannelPipeline`:   
`ChannelHandlerContext`:保存Channel相关的上下文信息，同事关联一个ChannelHandler对象，也就是说ChannelHandlerContext中也绑定了对应的pipeline和Channel的信息，
方便对ChannelHandler调用
`ChannelOption`
`EventLoopGroup`
`NioEventLoopGroup`
`Unpooled`:是netty提供的一个专门用来操作缓冲区的工具类（readerIndex,WriterIndex,Capacity）