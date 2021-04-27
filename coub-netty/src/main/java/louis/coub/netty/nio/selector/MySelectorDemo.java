package louis.coub.netty.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class MySelectorDemo {

    private static final Integer port = 10001;


    /**
     * 创建 nio 三部曲
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        registerChannel(selector);

        listenHandler(selector);



    }

    public static void listenHandler(Selector selector) {
        while (true) {
            try {
                int select = selector.select();
                System.out.println("本次触发的事件数为：" + select);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (!key.isValid()) {
                        iterator.remove();
                        continue;
                    }
                    handler(key, selector);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void handler(SelectionKey key, Selector selector) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);

        } else if (key.isReadable()) {

            /*
             * 读事件触发
             * 有从服务器端发送过来的信息，读取输出到屏幕上后，继续注册读事件
             * 监听服务器端发送信息
             */
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer rBuffer = ByteBuffer.allocate(1024);
            int count = client.read(rBuffer);
            if(count >0){
                String receiveText = new String( rBuffer.array(),0, count);
                System.out.println(receiveText);
                client = (SocketChannel) key.channel();
                client.register(selector, SelectionKey.OP_READ);
            }
        } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();

            while (buffer.hasRemaining()) {
                if (client.write(buffer)==0) {
                    break;
                }
            }

            client.close();


        }
    }

    public static ServerSocketChannel registerChannel(Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        ServerSocket socket = serverSocketChannel.socket();

        socket.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("server start on port :" + port);

        return serverSocketChannel;
    }
}
