//package com.louis.http;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
/// **
// * @author jun.liu
// * @since 2021/7/22 10:51
// */
//@Slf4j
//@Configuration
//public class RestTemplateConfig {
//
//    @Bean
//    public RestTemplate restTemplate(){
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate;
//    }
//
//    @Bean("urlConnectionTemplate")
//    public RestTemplate urlConnectionRestTemplate(){
//        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//        return restTemplate;
//    }
//
//    @Bean("httpClientTemplate")
//    public RestTemplate httpClientRestTemplate(){
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        return restTemplate;
//    }
//
//    @Bean("OKHttp3Template")
//    public RestTemplate OKHttp3RestTemplate(){
//        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//        return restTemplate;
//    }
//}
