package com.netty.inaction.chapter12;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author John·Louis
 * @date create in 2019/12/24
 * description:
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private  final String wsUrl;

    private static  String path;

    static {
//        获取URL的时候将空格转换了，以至于找不到index.html文件
        URL location = HttpRequestHandler.class.getProtectionDomain()
                .getCodeSource()
                .getLocation();
            try {
                path=location.toURI() + "index.html";
                path = !path.contains("%20") ? path : path.replace("%20", " ");
                path = !path.contains("file:") ? path : path.substring(5);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
    }
//    todo 无法在今天常量中定义，在静态代码块中实例化
    private static final File INDEX = new File(path);

    public HttpRequestHandler(String wsUrl) {
        this.wsUrl = wsUrl;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (wsUrl.equalsIgnoreCase(request.uri())) {
            ctx.fireChannelRead(request.retain());
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            DefaultHttpResponse httpResponse = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (keepAlive) {
                httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(httpResponse);
            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    private static void send100Continue(ChannelHandlerContext context) {
        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        context.writeAndFlush(httpResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
