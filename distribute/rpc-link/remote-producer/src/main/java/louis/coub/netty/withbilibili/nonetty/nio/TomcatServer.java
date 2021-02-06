package louis.coub.netty.withbilibili.nonetty.nio;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
public class TomcatServer {

    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private static List<SocketChannel> socketChannels = Lists.newArrayList();



    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8899);
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.configureBlocking(false);
            while (true) {
                for (SocketChannel socketChannel : socketChannels) {
                    int read = socketChannel.read(byteBuffer);
                    if (read>0) {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
