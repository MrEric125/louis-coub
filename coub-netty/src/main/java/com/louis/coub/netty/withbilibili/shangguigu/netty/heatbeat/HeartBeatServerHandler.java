package com.louis.coub.netty.withbilibili.shangguigu.netty.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;

/**
 * @author John·Louis
 * @date create in 2020/1/13
 * description:
 */
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

    }
}
