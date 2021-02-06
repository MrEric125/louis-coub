package louis.coub.netty.withbilibili.shangguigu.netty.webservice;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * @author John·Louis
 * @date create in 2020/1/13
 * description:
 * {@link TextWebSocketFrame} 表示一个文本帧
 */
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务端收到消息：{}", msg.text());
//      回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDate.now() + msg.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        id表示一个唯一的值 LoingText 是唯一的
        log.info("handler added 被调用：{}", ctx.channel().id().asLongText());
        log.info("handler added 被调用：{}", ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved 被调用: {}", ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
