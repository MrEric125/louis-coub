package com.config;

import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.WebHandler;
import reactor.netty.http.server.HttpServer;

/**
 * @author louis
 * <p>
 * Date: 2019/10/17
 * Description:
 */
public class WebFluxTest {

    HttpHandler handler = null;

    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);




}
