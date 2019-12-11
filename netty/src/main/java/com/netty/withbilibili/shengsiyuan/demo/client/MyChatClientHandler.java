package com.netty.withbilibili.shengsiyuan.demo.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author John·Louis
 * @date create in 2019/10/5
 * description:
 */
@ChannelHandler.Sharable
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(msg);


    }


}
