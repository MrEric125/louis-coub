
BIO ： 同步阻塞IO
    java.io包下的列
    InputStream 
    OutputStream
    Reader
    Writer
    
    网络编程
        ServerSocker
        Socket
    阻塞： read方法 accept 方法，阻塞式的
    
NIO: 同步非阻塞
    java.nio   包的类
    Buffer
        ByteBuffer
        CharBuffer
    Channel
        FileChannel
    
    网络编程
        ServerSocketChannel
        SocketChannel
        Selector
    非阻塞： read 方法，accept方法  可以设置阻塞还是非阻塞
    AsynchronousSocketChannel
    
    
    
1. 直接缓冲区， 将缓冲区建立在系统的物理内存中，在某种情况下是可以提高效率的
    非直接缓冲区： 建立在jvm的内存中    
                       