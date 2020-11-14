package com.luois.http;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author louis
 * <p>
 * Date: 2019/11/4
 * Description:
 */
public class ResponseTest {

    private final static HttpClient httpClient = HttpClientBuilder.create().build();
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        Optional.ofNullable(entity).map(x -> {
            try {
                return new BufferedHttpEntity(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
    public void test1() throws IOException {
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("", ""));
        formparams.add(new BasicNameValuePair("", ""));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost("");
        httpPost.setEntity(formEntity);
        httpClient.execute(httpPost);
    }
    public void test2() {
        CloseableHttpClient client=HttpClients.custom().addInterceptorLast((HttpRequestInterceptor) (httpRequest, httpContext) -> {
            AtomicInteger count = (AtomicInteger) httpContext.getAttribute("count");
            httpRequest.addHeader("Count", Integer.toString(count.getAndIncrement()));
        }).build();
        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContex = HttpClientContext.create();
        localContex.setAttribute("count", count);
        HttpGet httpGet = new HttpGet("");

    }


}
