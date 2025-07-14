//package com.louis.minaProject.jpa.controller;
//
//import org.apache.http.HttpException;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpRequest;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
//import org.apache.http.protocol.HttpContext;
//import org.springframework.boot.web.client.RestTemplateCustomizer;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @author jun.liu
// * @date created on 2020/11/2
// * description:
// */
//public class ProxyCustomizer implements RestTemplateCustomizer {
//    @Override
//    public void customize(RestTemplate restTemplate) {
//        HttpHost httpHost = new HttpHost("proxy.example.com");
//        HttpClient httpClientBuilder = HttpClientBuilder.create()
//                .setRoutePlanner(new DefaultProxyRoutePlanner(httpHost){
//                    @Override
//                    protected HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
//                        if (target.getHostName().equals("192.168.0.5"))
//                            return null;
//                        return super.determineProxy(target, request, context);
//                    }
//                }).build();
//
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClientBuilder));
//
//
//    }
//}
