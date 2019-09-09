package com.ajax;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author John·Louis
 * @date create in 2019/9/8
 * description:
 * 解决跨域的方式
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

//    @Bean
//    public FilterRegistrationBean registrationBean() {
//        FilterRegistrationBean bean= new FilterRegistrationBean();
//        bean.addUrlPatterns("/*");
//        bean.setFilter(new LouisCrosFilter());
//        return bean;
//    }
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("*");
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
////        configuration.addExposedHeader("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
//                .exposedHeaders("*");
    }
}
