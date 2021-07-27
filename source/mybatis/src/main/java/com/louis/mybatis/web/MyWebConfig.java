package com.louis.mybatis.web;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Configuration
@EnableWebMvc
public class MyWebConfig implements WebMvcConfigurer {

    /**
     *
     */
    @NumberFormat
    public Integer integer;

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder().indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(mapperBuilder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(mapperBuilder.createXmlMapper(true).build()));

    }

    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {
        FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();
        Set<MyConverter> hashSet = Sets.newHashSet();
        conversionService.setConverters(hashSet);
        conversionService.setFormatters(Sets.newHashSet());

        return conversionService;

    }

    class MyConverter {

    }

    class MyAnnotaionFormatterFactory {
    }

    class MyFormatter {

    }
}
