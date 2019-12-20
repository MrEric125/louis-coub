###  nio
 1. 每个channel 一般对应一个buffer
 2.一个selector 对应一个线程  一个selector 对应过个channel
 3.nio 网络模型，反应的是channel 注册到selector的过程
 4. 程序切换到哪个channel 是由事件（Event）决定的 event 只是一个概念 分工的概念
 5. selector 是由不同的时间在不同的通道上面切换的
 6. buffer就是一个内存块，底层其实就是一个数组
 7. 数据的读写都是通过buffer, buffer 是可以读也可以写，但是需要flip切换一下
 8. channel是双向的，可以范湖底层操作系统的情况，比如linux底层操作系统通道就是双向的


buffer 
 buffer本质上是一个可以读写数据的内存块，可以理解陈格式一个容器对象（array）该对象提供了一组方法，可以更轻松的使用内存块，缓冲区对象内置了一些机制，能够跟踪和记录缓冲区的状态变化情况，channel提供了从文件网络读取数据的通道，但是读取和写入的数据都是经过buffer
 