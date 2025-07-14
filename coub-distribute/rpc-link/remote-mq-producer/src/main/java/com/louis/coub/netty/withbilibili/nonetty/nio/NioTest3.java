package com.louis.coub.netty.withbilibili.nonetty.nio;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/10/21
 * description:
 */
public class NioTest3 {

    @Test
    public void test() throws Exception{
//        获取的是编译完之后的目录（target目录中的resource）
        ClassPathResource resource = new ClassPathResource("NioTest3.txt");
        File file = resource.getFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(4, (byte) 'b');

    }

    /**
     * 传递buffer数组
     * @throws IOException
     */
    public static void main(String[] args)throws IOException {


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long r = socketChannel.read(buffers);
                byteRead += r;

                System.out.println("bytesRead: " + byteRead);

                Arrays.asList(buffers).forEach(x->{
                    String s = "position: " + x.position() + ", limit: " + x.limit();
                    System.out.println(s);

                });
            }
            Arrays.asList(buffers).forEach(Buffer::flip);

            long byteWritten = 0;

            while (byteWritten < messageLength) {
                long r = socketChannel.write(buffers);
                byteWritten += r;

            }
            Arrays.asList(buffers).forEach(Buffer::clear);


            System.out.println(" byteRead: " + byteRead + ", bytesWritten: " + byteWritten + ".messageLength: " + messageLength);
        }
    }
}
