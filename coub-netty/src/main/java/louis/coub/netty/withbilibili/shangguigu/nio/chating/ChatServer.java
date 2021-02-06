package louis.coub.netty.withbilibili.shangguigu.nio.chating;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author John·Louis
 * @date create in 2019/12/22
 * description:
 */
public class ChatServer {


    private Selector selector;

    private ServerSocketChannel listener;

    private static final int PORT = 6667;

    public ChatServer() {

        try {
            selector = Selector.open();
            listener = ServerSocketChannel.open();


            listener.socket().bind(new InetSocketAddress(PORT));

            listener.configureBlocking(false);

            listener.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.listen();

    }

    public void listen() {
        try {

            while (true) {
//                相当于Reactor 单线程中的Reactor,
                int count = selector.select(2000);
//                有事件处理
                if (count > 0) {
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey selectionKey = keyIterator.next();
                        //                相当于Reactor 单线程中的建立连接
                        if (selectionKey.isAcceptable()) {
                            System.out.println("处理连接的线程:"+Thread.currentThread().getName());
                            SocketChannel socketChannel = listener.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress()+" 上线了");
                        }
                        //                相当于Reactor 单线程中处理请求
                        if (selectionKey.isReadable()) {
                            System.out.println("处理读请求:"+Thread.currentThread().getName());
                            readContent(selectionKey);
                        }
//                        将事件从迭代器中移除
                        keyIterator.remove();

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    读取客户端消息
    private void readContent(SelectionKey selectionKey) {
//        定义一个socketChannel
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.configureBlocking(false);

            int read = socketChannel.read(byteBuffer);
            String msg = new String(byteBuffer.array());
            if (read>0) {
                System.out.println("from 客户端：" + msg);

//                转发消息
                sendInfoToOthers(msg, socketChannel);
            }

        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + " 离线了");
                selectionKey.cancel();
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendInfoToOthers(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息");
        System.out.println("转发线程" + Thread.currentThread().getName());
//        获取所有Select上注册的SocketChannel
        for (SelectionKey selectionKey : selector.keys()) {
            SelectableChannel targetChannel = selectionKey.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
//                将msg存储为一个Buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }

    }
}
