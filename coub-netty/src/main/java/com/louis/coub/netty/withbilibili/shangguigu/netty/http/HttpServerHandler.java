package com.louis.coub.netty.withbilibili.shangguigu.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author John·Louis
 * @date create in 2020/1/7
 * description:
 * 指定传输的是http相关的类型
 * {@link HttpObject} 客户端和服务端相互通信的数据都被封装成一个HttpObject
 */
@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            log.info("msg 类型：{}" , msg.getClass());
            log.info("客户端地址：{}", ctx.channel().remoteAddress());
            log.info("url:{}", ((HttpRequest) msg).uri());


//          回复数据
            ByteBuf responseContent = Unpooled.copiedBuffer("Hello i am server", CharsetUtil.UTF_8);
//           构建一个响应 状态码为200
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, responseContent);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain")
                    .set(HttpHeaderNames.CONTENT_LENGTH, responseContent.readableBytes());


//            发送构建好的数据
            ctx.writeAndFlush(response);

        }
    }
}
