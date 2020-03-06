package com.luois.http;

import com.google.common.collect.Lists;
import org.apache.coyote.Request;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author louis
 * <p>
 * Date: 2019/10/29
 * Description:
 */
public class HttpClientTest {


    private CloseableHttpClient client;

    @Before
    public void buildClient() {
//        client=HttpClientBuilder.create().build();
        client = HttpClientBuilder.create().setMaxConnPerRoute(5)
                .setMaxConnTotal(5).build();
    }

    @After
    public void close() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testGet() {
        HttpGet get = new HttpGet("http://localhost:8113/login");
        get.setHeader("Connection","keep-alive");

        CloseableHttpResponse response;
        try {
            response=client.execute(get);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);

            System.out.println("============================================");
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void postTest1() {
        HttpPost httpPost = new HttpPost("");
        List<NameValuePair> nvps = Lists.newArrayList();
        CloseableHttpResponse response;
        try {
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            response=client.execute(httpPost);
            response.getEntity();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI("https://someportal/"))
                    .addHeader("", "")
                    .addParameter("", "").build();
            client.execute(login);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRequestFuture() throws ExecutionException, InterruptedException, TimeoutException, IOException {
        // the simplest way to create a HttpAsyncClientWithFuture
        HttpClient httpclient = HttpClientBuilder.create()
                .setMaxConnPerRoute(5)
                .setMaxConnTotal(5).build();
        ExecutorService execService = Executors.newFixedThreadPool(5);
        FutureRequestExecutionService requestExecService = new FutureRequestExecutionService(
                httpclient, execService);
        try {
            // Because things are asynchronous, you must provide a ResponseHandler
            ResponseHandler<Boolean> handler = response -> {
                // simply return true if the status was OK
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                return response.getStatusLine().getStatusCode() == 200;
            };

            // Simple request ...
            HttpGet request1 = new HttpGet("http://httpbin.org/get");


            HttpRequestFutureTask<Boolean> futureTask1 = requestExecService.execute(request1,
                    HttpClientContext.create(), handler);
            Boolean wasItOk1 = futureTask1.get();
            System.out.println("============================================");
            System.out.println("It was ok? "  + wasItOk1);
            System.out.println("============================================");

            // Cancel a request
            try {
                HttpGet request2 = new HttpGet("http://httpbin.org/get");
                HttpRequestFutureTask<Boolean> futureTask2 = requestExecService.execute(request2,
                        HttpClientContext.create(), handler);
                futureTask2.cancel(true);
                Boolean wasItOk2 = futureTask2.get();
                System.out.println("============================================");
                System.out.println("It was cancelled so it should never print this: " + wasItOk2);
                System.out.println("============================================");
            } catch (CancellationException e) {
                System.out.println("============================================");
                System.out.println("We cancelled it, so this is expected");
                System.out.println("============================================");
            }

            // Request with a timeout
            HttpGet request3 = new HttpGet("http://httpbin.org/get");
            HttpRequestFutureTask<Boolean> futureTask3 = requestExecService.execute(request3,
                    HttpClientContext.create(), handler);
            Boolean wasItOk3 = futureTask3.get(10, TimeUnit.SECONDS);
            System.out.println("It was ok? "  + wasItOk3);

            FutureCallback<Boolean> callback = new FutureCallback<Boolean>() {
                @Override
                public void completed(Boolean result) {
                    System.out.println("============================================");
                    System.out.println("completed with " + result);
                    System.out.println("============================================");
                }

                @Override
                public void failed(Exception ex) {
                    System.out.println("============================================");
                    System.out.println("failed with " + ex.getMessage());
                    System.out.println("============================================");
                }

                @Override
                public void cancelled() {
                    System.out.println("============================================");
                    System.out.println("cancelled");
                    System.out.println("============================================");
                }
            };

            // Simple request with a callback
            HttpGet request4 = new HttpGet("http://httpbin.org/get");
            // using a null HttpContext here since it is optional
            // the callback will be called when the task completes, fails, or is cancelled
            HttpRequestFutureTask<Boolean> futureTask4 = requestExecService.execute(request4,
                    HttpClientContext.create(), handler, callback);
            Boolean wasItOk4 = futureTask4.get(10, TimeUnit.SECONDS);
            System.out.println("============================================");
            System.out.println("It was ok? "  + wasItOk4);
            System.out.println("============================================");
        } finally {
            requestExecService.close();
        }


    }

    @Test
    public void testPost() throws UnsupportedEncodingException {
        HttpPost post = new HttpPost("localhost:8113/login");
        CloseableHttpResponse response = null;
        try {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            File file = new File("");
            multipartEntityBuilder.addBinaryBody("files", file, ContentType.DEFAULT_BINARY, URLEncoder.encode(file.getName(), "UTF-8"));
            HttpEntity entity = multipartEntityBuilder.build();
            post.setEntity(entity);
            response=client.execute(post);
            HttpEntity responseEntity = response.getEntity();
            response.getStatusLine();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (response != null) {
                    response.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
