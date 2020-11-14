package com.luois.http;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author louis
 * <p>
 * Date: 2019/11/1
 * Description:
 */
public class HttpClientFundamentals {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "httpclient")
                .build();
        HttpGet httpGet = new HttpGet(uri);
        System.out.println(httpGet.getURI());

        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        HttpEntity httpEntity = new StringEntity("this is response");
        response.setEntity(httpEntity);
        System.out.println(response.getStatusLine().toString());
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
        if (response.getEntity() != null) {
            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);

        }
        String responHeader = Optional.ofNullable(response.getFirstHeader("name"))
                .map(NameValuePair::getValue).orElse(null);
        System.out.println(responHeader);
        String entityString = Optional.ofNullable(response.getEntity()).map(wrap(EntityUtils::toString)).orElse(null);
        System.out.println(entityString);

    }

    private static <T,R>Function<T,R> wrap(CheckFunction<T, R> wrap){
        return t -> {
            try {
                return wrap.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
//                throw e;
            }
        };
    }

    public void test2() {
        File file = new File("");
        FileEntity fileEntity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
        HttpPost httpPost = new HttpPost();
        httpPost.setEntity(fileEntity);
    }

}
