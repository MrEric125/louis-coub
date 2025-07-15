package com.louis.coub.netty.withbilibili.shangguigu.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author John·Louis
 * @date create in 2019/12/10
 * description:
 * <p>
 * 处理业务逻辑
 */
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

    }

    /**
     * 发送数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client  发送数据===》");
        ctx.writeAndFlush(1234567890L);
    }
}
