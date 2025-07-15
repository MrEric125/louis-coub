package com.louis.coub.netty.withbilibili.shangguigu.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author John·Louis
 * @date create in 2019/12/22
 * description:
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
//        得到一个网络连接
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        if (!socketChannel.connect(new InetSocketAddress("localhost", 6666))) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接不成功， 但是不会阻塞");
            }
        }
//        连接成功就发送数据
        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());

//        发送数据

        socketChannel.write(buffer);

        System.out.println("发送完成" + socketChannel.toString());


//        System.in.read();

    }
}
