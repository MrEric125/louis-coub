package louis.coub.netty.withbilibili.shengsiyuan.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author John·Louis
 * @date create in 2019/10/5
 * description:
 *  初始化，绑定handler这是netty 流程都是这样的
 *
 *  netty能做什么？？
 *  1.作为一个web服务器，只是netty 并没有实现一个server规范， netty 并没有对请求路由做任何事
 *  2.底层socket开发，方便数据传输，还能支持自定义协议
 *  3.长连接数据传输
 */
public class MyChatServer {

    public static void main(String[] args) throws InterruptedException {

        //完成连接工作
        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        完成处理任务工作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
//                    关联一个子处理器
                    .childHandler(new MyChatServerInitializer());

//        绑定端口
            ChannelFuture channelFuture = bootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }











    }

}
