
##java 原生的nio 的问题

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