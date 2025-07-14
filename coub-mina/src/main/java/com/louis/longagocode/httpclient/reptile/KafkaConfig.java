package com.louis.longagocode.httpclient.reptile;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author John·Louis
 * @date create in 2019/12/15
 * description:
 */
@Slf4j
public class KafkaConfig {

    public static void main(String[] args) {
        String url = "https://kafka.apache.org/documentation/#configuration";
        String htmlByUrl = getHtmlByUrl(url);
        System.out.println("ok");
    }

    private static String getHtmlByUrl(String url) {

        String html = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode== HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                html = Optional.ofNullable(entity).map(entity1 -> {
                    try {
                        return EntityUtils.toString(entity1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).orElseThrow(() -> new NullPointerException("响应体为空"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }


}
