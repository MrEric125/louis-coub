package com.louis.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author jun.liu
 * @since 2021/1/11 14:53
 */
@Slf4j
public class HttpClient2 {


    private static CloseableHttpClient httpClient;

    private static PoolingHttpClientConnectionManager manager; // 连接池管理类

    private static ScheduledExecutorService monitorExecutor; // 监控

    private final static Object syncLock = new Object(); // 相当于线程锁,用于线程安全

    private static final int CONNECT_TIMEOUT = 10000;// 设置连接建立的超时时间为10s

    private static final int SOCKET_TIMEOUT =10000;

    private static final int MAX_CONN = 10; // 最大连接数

    private static final int Max_PRE_ROUTE = 30;

    private static final int MAX_ROUTE = 30;

    /**
     * 根据host和port构建httpclient实例
     *
     * @return
     */

    public static CloseableHttpClient createHttpClient() {

        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();

        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()

                .register("http", plainSocketFactory).register("https", sslSocketFactory).build();
        manager = new PoolingHttpClientConnectionManager(registry);

        // 设置连接参数
        manager.setMaxTotal(MAX_CONN); // 最大连接数

        manager.setDefaultMaxPerRoute(Max_PRE_ROUTE); // 路由最大连接数

//        HttpHost httpHost = new HttpHost(host, port);
//        manager.setMaxPerRoute(new HttpRoute(httpHost), MAX_ROUTE);

        // 请求失败时,进行请求重试
        HttpRequestRetryHandler handler = (e, i, httpContext) -> {
            if (i > 3) {
                // 重试超过3次,放弃请求
                log.error("retry has more than 3 time, give up request");
                return false;
            }
            if (e instanceof NoHttpResponseException) {

                // 服务器没有响应,可能是服务器断开了连接,应该重试

                log.error("receive no response from server, retry");

                return true;
            }

            if (e instanceof SSLHandshakeException) {


                log.error("SSL hand shake exception");

                return false;

            }

            if (e instanceof InterruptedIOException) {


                log.error("InterruptedIOException");

                return false;
            }

            if (e instanceof UnknownHostException) {

                // 服务器不可达

                log.error("server host unknown");

                return false;

            }

            if (e instanceof ConnectTimeoutException) {

                // 连接超时

                log.error("Connection Time out");
                return false;
            }

            if (e instanceof SSLException) {

                log.error("SSLException");
                return false;
            }

            HttpClientContext context = HttpClientContext.adapt(httpContext);

            HttpRequest request = context.getRequest();

            if (!(request instanceof HttpEntityEnclosingRequest)) {

                // 如果请求不是关闭连接的请求

                return true;
            }
            return false;
        };

        return HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler)
                .build();

    }

    /**
     * 关闭连接池
     */

    public static void closeConnectionPool() {

        try {

            httpClient.close();

            manager.close();

            monitorExecutor.shutdown();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
