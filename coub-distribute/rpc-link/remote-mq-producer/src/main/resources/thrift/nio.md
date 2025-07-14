 java.io
 java.nio
 
 
java.io的核心概念是（Stream）.面向流的编程（要么是输入流，要么是输出流）
java.nio 的核心概念有三个，（Selector,Channel,Buffer）,在java.nio中是面向块（Block）和缓冲区（Buffer）编程的
buffer,其实就是一块内存，底层上的实现其实就是一个数组，数据的读与写，都是通过Buffer来实现的，Buffer既可以读也可以写

一般读到的数据都是来自channel,将数据从channel进入Buffer,然后我们再从Buffer中获取数据

除了数组之外，Buffer还提供了了对于数据的结构化访问方式，并且还可以追踪到系统的读写过程

Channel是值得可以通过向其中写入数据或是从其中读取数据的对象，它类似java.io中的stream

所有的数据的读写都是通过Buffer来进行的，永远不可能出现直接向Channel中写入数据的情况，或者直接同Channel读取数据的情况

由于Channel是双向的，因此它能够更好的反映操作系统底层的执行情况

0<= mark <= position <= limit <=capacity

通过NIO读取文件涉及到三个不住


1. 从FileInputStream 获取到FileChannel 对象
2. 创建Buffer
3. 将数据从Channel读取到Buffer中

绝对方法与相对方法的含义：
 1. 相对方法:  limit值与position值会在操作时会被考虑到
 2. 绝对方法，完全忽略到limit和Position的值