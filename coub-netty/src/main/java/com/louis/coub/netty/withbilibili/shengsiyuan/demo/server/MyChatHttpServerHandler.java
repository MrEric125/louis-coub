package com.louis.coub.netty.withbilibili.shengsiyuan.demo.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author John·Louis
 *  create in 2019/10/4
 * description:
 * 自己定义的处理器
 */
public class  MyChatHttpServerHandler extends SimpleChannelInboundHandler<String> {

//    保存建立连接的channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg){

        Channel channel = ctx.channel();
        channelGroup.forEach(ch->{
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + " 发送的消息: " + msg + "\n");
            } else {
                ch.writeAndFlush("[自己]" + msg);
            }
        });


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 服务端和客户端建立连接
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        Channel channel = ctx.channel();
        channelGroup.flushAndWrite("[服务器]- "+ channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)  {
        Channel channel = ctx.channel();
        channelGroup.flushAndWrite("[服务器]- "+ channel.remoteAddress() + " 离开\n");
        System.out.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx)  {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 下线");
    }
}
