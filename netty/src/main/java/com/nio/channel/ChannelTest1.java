package com.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * @author louis
 * <p>
 * Date: 2019/12/6
 * Description:  javaNIO书中的例子
 * 创建Channel有多种方式，
 * 1. Socket创建Channel有socket通道的工厂方法
 * 2. FileChannel创建只能通过RandomAccessFile， FileOutputStream,FileInputStream的getChannel()来创建
 *
 */
public class ChannelTest1 {

    public static void main(String[] args) throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
//        channelCopy1(source, dest);
        channelCopy2(source, dest);
        source.close();
        dest.close();


    }

    /**
     * 将src的数据复制到desc
     * 这种方式使用{@link ByteBuffer#compact()} 使用了数据复制，减少了系统调用
     * @param src
     * @param desc
     * @throws IOException
     */
    public static void channelCopy1(ReadableByteChannel src, WritableByteChannel desc) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            desc.write(buffer);
//            如果忘记了，请查阅资料，掌握flip/compact/allocateDirect的功能是什么
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining())
            desc.write(buffer);
    }
    /**
     * 和copy1的功能点相同，可以具体比较不同点 {@link ChannelTest1#channelCopy1(ReadableByteChannel, WritableByteChannel)}
     * 不需要舒服复制，但是可能会造成更多的系统调用
     * @param src
     * @param desc
     * @throws IOException
     */
    public static void channelCopy2(ReadableByteChannel src, WritableByteChannel desc) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(byteBuffer) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining())
                desc.write(byteBuffer);
            byteBuffer.clear();
        }
    }
    public static Channel fileChannel(String srcdir) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(srcdir);
        return fis.getChannel();
    }
    public static SocketChannel socketChannel() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8090));
        return sc;
    }
    public static ServerSocketChannel serverSocketChannel() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        return ssc;
    }
    public static DatagramChannel datagramChannel() throws IOException {
        return DatagramChannel.open();
    }
}
