package louis.coub.netty.withbilibili.shangguigu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author John·Louis
 * @date create in 2019/12/28
 * description:
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //完成连接工作,包含一个或者多个EventLoop,一个EventLoop的生命周期内只和Thread绑定
        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        完成处理任务工作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

//          注册服务器  一个Chanel在生命周期内只注册一个EventLoop
            bootstrap.group(bossGroup, workerGroup)
//                    这个地方如果加了handler,那么就是在bossGroup中使用的
                    .handler(null)
//                    使用nioServerSocketChannel作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
//                    设置下连城队列到的线程个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
//                    关联一个子处理器,常用于业务处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(null);
                        }
                    });

//        绑定端口
            ChannelFuture channelFuture = bootstrap.bind(8899).sync();
//            对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
