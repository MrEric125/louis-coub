package com.config;

import com.server.handler.PersonHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 */

@Configuration
public class WebFluxConfig  {


//    @Bean
//    public WebHandler webHandler(ApplicationContext applicationContext) {
//        return new DispatcherHandler(applicationContext);
//    }

//    @Bean
//    public RouterFunction<ServerResponse> monoRouterFunction(PersonHandler userHandler){
//        return route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)),userHandler::getAllUser)
//                .andRoute(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)),userHandler::getUserById)
//                .andRoute(POST("/api/save").and(accept(MediaType.APPLICATION_JSON)),userHandler::saveUser);
//    }



}
