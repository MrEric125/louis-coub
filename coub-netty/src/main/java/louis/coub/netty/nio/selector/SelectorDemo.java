package louis.coub.netty.nio.selector;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;


/**
 * {@link java.nio.channels.Selector}: 管理着一个被注册的通道集合的信息和他们的就绪状态
 *
 * channel是和Selector 一起被注册的，并且使用选择起来更新通道的就绪状态，当我们这么操作的时候，可以选择
 *
 * 讲被激发的线程挂起，一直等到有就绪的通道
 *
 * {@link java.nio.channels.SelectableChannel} 提供了实现通道的可选择性所需要的公共方法。它是所有支持
 *
 * 就绪检查（todo 什么是就绪检查 ）的通道类的父类
 *
 * SelectableChannel可以被注册到Selector对象上，同时可以制定对哪个选择器而言，那种操作是感兴趣的，一个通道可以被
 *
 * 注册到多个选择器上，但是在每个选择器上只能注册一次
 *
 * {@link java.nio.channels.SelectionKey} 选择键封装了特定的同道与特定的选择器的注册关系，选择键对象被SelectableChannel.register()
 *
 * 返回并提供一个表示这种注册关系的标记。选择键包含了两个byte集，指示了该注册关系所关心的同道操作，以及通道已经准备好的操作
 *
 * SelectionKey;
 * Selector;
 * ServerSocketChannel;
 * SocketChannel;
 * todo 以上四个类的关系，如何协同处理
 *
 */
public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();

        Selector selector = Selector.open();
        socket.bind(new InetSocketAddress(98761));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel accept = channel.accept();
                    registerChannel(selector, accept, SelectionKey.OP_READ);
                    sayHello(accept);
                }
                if (next.isReadable()) {
                    readDataFromSocket(next);
                }
                iterator.remove();
            }
        }
    }

    private static void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear();

        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear();
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private static void sayHello(SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    private static ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    private static void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }


}
