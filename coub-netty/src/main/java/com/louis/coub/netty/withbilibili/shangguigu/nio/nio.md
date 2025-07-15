### nio

1. 每个channel 一般对应一个buffer
   2.一个selector 对应一个线程 一个selector 对应过个channel
   3.nio 网络模型，反应的是channel 注册到selector的过程
4. 程序切换到哪个channel 是由事件（Event）决定的 event 只是一个概念 分工的概念
5. selector 是由不同的时间在不同的通道上面切换的
6. buffer就是一个内存块，底层其实就是一个数组
7. 数据的读写都是通过buffer, buffer 是可以读也可以写，但是需要flip切换一下
8. channel是双向的，可以范湖底层操作系统的情况，比如linux底层操作系统通道就是双向的

### buffer

buffer本质上是一个可以读写数据的内存块，可以理解陈格式一个容器对象（array）该对象提供了一组方法，
可以更轻松的使用内存块，缓冲区对象内置了一些机制，能够跟踪和记录缓冲区的状态变化情况，
channel提供了从文件网络读取数据的通道，但是读取和写入的数据都是经过buffer

buffer 中的重点知识 Capacity Limit Position mark

常用方法  
allocate(int)  初始化一个容量     
buffer.flip();     
get(int)       
get()      
put(byte)      
put(int,byte)

MappedByteBuffer 可让文件直接在内存中（堆外内存）中修改 操作系统不需要拷贝
  
===============================================================    

### channel(可以通过 ctrl+H 来查看类的击沉关系图)

常用channel
FileChannel 文件读写操作
DatagramChannel udp数据读写
ServerSocketChannel tcp数据的读写
SocketChannel tcp数据的读写

常见方法：
read(ByteBuffer) 从通道读取数据并放到缓冲区中
write(ByteBuffer) 把缓冲区的数据写到通道中
transferFrom(ReadableByteChannel,long,count) 从目标通道中复制数据到当前通道
transferTo(long，long WritableByteChannel) 把数据从当前通道复制给目标通道中去
map(FileChannel.MapMode,long,long)

### selector 选择器

java 的NIO 操作，用的是非阻塞的IO方式，可以用一个线程，处理多个客户端的连接
selector能够检测多个注册的通道上是否有时间发生(多个channel以时间的方式可以注册到同一个Selector上)
如果有时间发生，便获取时间然后针对每个时间进行相应的处理
这样就可以只用一个单线程去管理多个通道，这就是管理多个连接和请求的方式
只有在连接真正有读写时间发生时，才会进行读写，就大大的减少了系统的开销，并且不必为每个连接都创建一个线程，不用去维护多个线程1，避免了线程之间的上下文切换的开销

1. 当客户端连接时，会通过serverSocketChannel 得到SocketChannel
2. 当socketChannel注册到Selector上，register(Selector,int) 会返回一个SelectionKey,这个key会和该Selector关联
   每个selector上都可以注册多个SocketChannel
3. Selector会监听select方法，返回有事件发生的通道
5. 可以进一步得到各个SelectionKey 表示有事件发生
6. 再通过SelectKey 反向获取SocketChannel 方法channel()
7. 可以通过channel() 得到channel 完成业务处理

open() 创建一个选择器对象
select(long) 江空所有注册的通道，当其中有IO操作可以进行时，将对应的SelectionKey加入到内部稽核中并返回，参数用来设置超时时间
wakeup()
selectNow()
selectorKeys()

### SelectionKey

### SocketChannel

网络IO通道，具体负责进行读写操作，NIO 把缓冲区的数据些人通道，或者吧通道里面的数据读取到缓冲区
重要方法
open()
configureBlocking(boolean)
connect(SocketAddress)
finishConnect()
write(ByteBuffer)
register(Selector,int,Object)

### 零拷贝

零拷贝是网络编程的关键 很多性能优化都离不开它
java程序中 常用的零拷贝有mmap(内存映射) 和sendFile

传统的IO模型里面 一次读写 有四次数据拷贝 三次切换（用户->内核，内核->用户 用户->内核 内核发送数据）
mmap
通过内存映射，可以将文件映射到内核缓冲区中，同事用户控件可以共享内核控件的数据，这样，在进行网络传输时，就可以减少内核控件到用户控件的拷贝次数
会有三次数据拷贝 三次切换

sendFile
2.1 版本
三次数据拷贝 两次切换
2.4 版本 （真正的零拷贝）
避免了从内核缓冲区拷贝到Socket buffer的操作，直接拷贝到协议栈，从而再次减少了数据拷贝
两次数据拷贝 两次切换

1.我们说零拷贝，是从数据拷贝的角度来说的，因为内核缓冲区之间，没有数据是重复的（只有kernel buffer 有一份数据）

2. 零拷贝不仅仅带来了更少的数据复制，还能带啦其它的性能优势，例如上下文切换，更少的CPU缓存，   
   mmap 适合小数据量的读写 sendFile 适合文件传输

nio中的重要知识点 byteBuffer channel selector 零拷贝
  