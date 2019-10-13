## NIO 编程
### webSocket 的使用和理解

WebSocket 其实是一个规范，主要是针对于Http的无状态，这种特性，http 是正对于请求与响应的，而这两个链接建立之后 才会处理数据
http 1.1 之后新增了一个keep alive， 可以重用既有的连接

如果基于http 那么是无法实现聊天这种实时广播的功能

webSocket 能够实现客户端与服务端之间的长连接，能够实现服务端向服务端之间push数据，而且可以直接传输数据，而不需要冗余的头信息
webSocket 是真正的全双工的长连接

Apache Thrift 

Google Protobuf  ===>GRPC

rm: remote method invocation  只针对 java

client :stub

server: skeleton

序列化和反序列化 也被称之为编码和解码

RPC remote Proceduce Call 远程过程调用，RPC 一般可以跨语言
1. 定义一个借口说明文件：描述了对象（结构体）、对象成员、接口方法等一系列信息
2. 通过RPC 框架所提供的的编码器，将接口说明文件编译成具体语言文件
3.在客户端与服务器端分别引入RPC编译器所产生的文件，即可像调用本地方法一样调用远程方法 


