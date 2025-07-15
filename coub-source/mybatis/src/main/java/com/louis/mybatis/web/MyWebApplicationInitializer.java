package com.louis.mybatis.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author jun.liu
 * @see org.springframework.web.servlet.HandlerMapping 主要的两个接口功能
 * Map a request to a handler along with a list of interceptors{@link } for pre- and post-processing.
 * The mapping is based on some criteria, the details of which vary by HandlerMapping implementation.
 * <p>
 * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping}：
 * 主要是解析{@link org.springframework.web.bind.annotation.RequestMapping}
 * <p>
 * {@link org.springframework.web.servlet.handler.SimpleUrlHandlerMapping}
 * which maintains explicit registrations of URI path patterns to handlers
 * <p>
 * spring web Mvc context,about detail to see
 * <a>https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc-servlet-context-hierarchy </a>
 * @since 2020/11/2
 * description:
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MyWebConfig.class);
        applicationContext.refresh();


        DispatcherServlet servlet = new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/**");

    }
}

class MyHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return false;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }
}

/**
 * Strategy to resolve exceptions, possibly mapping them to handlers, to HTML error views, or other targets. ,See
 * <a>https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc-exceptionhandlers</a>
 */
class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return null;
    }
}

/**
 * detail to see
 * <a>https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc-themeresolver </a>
 */
class MyThemeResolve implements ThemeResolver {

    @Override
    public String resolveThemeName(HttpServletRequest request) {
        return null;
    }

    @Override
    public void setThemeName(HttpServletRequest request, HttpServletResponse response, String themeName) {

    }
}

/**
 * Abstraction for parsing a multi-part request (for example, browser form file upload) with the help of some multipart parsing library.
 * See Multipart Resolver(<a>https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc-multipart</a>).
 */
class MyMultiparResolver implements MultipartRequest {

    @Override
    public Iterator<String> getFileNames() {
        return null;
    }

    @Override
    public MultipartFile getFile(String name) {
        return null;
    }

    @Override
    public List<MultipartFile> getFiles(String name) {
        return null;
    }

    @Override
    public Map<String, MultipartFile> getFileMap() {
        return null;
    }

    @Override
    public MultiValueMap<String, MultipartFile> getMultiFileMap() {
        return null;
    }

    @Override
    public String getMultipartContentType(String paramOrFileName) {
        return null;
    }
}
