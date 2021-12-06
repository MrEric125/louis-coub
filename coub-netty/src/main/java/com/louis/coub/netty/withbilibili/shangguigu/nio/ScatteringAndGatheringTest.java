package com.louis.coub.netty.withbilibili.shangguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/12/22
 * description:
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8989));

        ByteBuffer[] byteBuffers = new ByteBuffer[2];

        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

//        等待客户端连接 使用telnet
        SocketChannel socketChannel = serverSocketChannel.accept();
//      循环读取数据
        int messageLength = 8;
        while (true) {
            long byteRead = 0;
            while (byteRead < messageLength) {
                byteRead= socketChannel.read(byteBuffers);
                byteRead += byteRead;
                Arrays.stream(byteBuffers)
                        .map(byteBuffer -> "position=" + byteBuffer.position() + ",limit=" + byteBuffer.limit())
                        .forEach(System.out::println);

            }
            Arrays.stream(byteBuffers).forEach(Buffer::flip);

            long byteWrite = 0;
            while (byteWrite<messageLength){
                byteWrite += socketChannel.read(byteBuffers);
            }


        }
    }
}
