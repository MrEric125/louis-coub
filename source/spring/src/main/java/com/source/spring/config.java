package com.source.spring;

import com.source.spring.argumentResolver.SearchArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Louis
 * @date created on 2021/1/18
 * description:
 */
@Configuration
public class config  implements WebMvcConfigurer {


    @Autowired
    private SearchArgumentResolver searchArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(searchArgumentResolver);

    }
}
