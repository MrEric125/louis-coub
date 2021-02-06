package louis.coub.netty.book.inaction.chapter12;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author John·Louis
 * @date create in 2019/12/29
 * description:
 * 使用webSocket
 */
@Slf4j
public class ChatServer {

    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    private final EventLoopGroup group = new NioEventLoopGroup();

    private Channel channel;

    public ChannelFuture start(InetSocketAddress address) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup));
        ChannelFuture channelFuture = bootstrap.bind(address);
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
        return channelFuture;

    }

    private ChannelHandler createInitializer(ChannelGroup channelGroup) {
        return new ChatServerInitializer(channelGroup);
    }

    public static void main(String[] args) {
        int port = 9999;
        if (args.length != 1) {
            log.warn("no gived port use default port:{}", port);
//            System.exit(1);
        } else {
            port = Integer.parseInt(args[0]);
        }
        ChatServer chatServer = new ChatServer();
        ChannelFuture future = chatServer.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread(chatServer::destroy));
        future.channel().closeFuture().syncUninterruptibly();
    }

    private void destroy() {
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();
        group.shutdownGracefully();
    }

}
