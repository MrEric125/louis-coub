package com.netty.withbilibili.shangguigu.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author John·Louis
 * @date create in 2020/1/7
 * description:
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        处理http相关的编解码器
        pipeline.addLast(new HttpServerCodec());
//        增加自定义的handler
        pipeline.addLast(new HttpServerHandler());


    }
}
