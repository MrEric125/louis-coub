package com.netty.withbilibili.shangguigu.handler.client;

import com.netty.withbilibili.shangguigu.handler.MyLongToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author John·Louis
 * @date create in 2019/12/10
 * description:
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        在piperline加入出站的handler, 对数据进行编码
        pipeline.addLast(new MyLongToByteEncoder());
//        加入自己的handler
        pipeline.addLast(new MyClientHandler());

    }
}
