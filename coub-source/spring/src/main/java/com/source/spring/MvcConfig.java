package com.source.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Louis
 * @date created on 2021/1/18
 * description:
 */

@Configuration
@EnableWebMvc
@ComponentScan(value = "com.source.spring")
public class MvcConfig implements WebMvcConfigurer {


//    @Autowired
//    private SearchArgumentResolver searchArgumentResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(searchArgumentResolver);
//
//    }
//
//    //添加一个ViewResolver解析view
//    @Bean//相当于Spring配置文件bean节点
//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/");
//        viewResolver.setSuffix(".jsp");
////        viewResolver.setViewClass(JstlView.class);
//        return viewResolver;
//    }
//    //注册静态资源，没注册的话，网站是访问不了的
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
//        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
//        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/fonts/");
//    }
//    //根目录的时候直接跳转到登录界面
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/");
//    }
}
