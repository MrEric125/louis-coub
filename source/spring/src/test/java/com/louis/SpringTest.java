package com.louis;

import com.louis.annotation.AnnotationClass;
import com.source.spring.mvc.DemoParam;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.springframework.util.StringUtils.uncapitalize;

public class SpringTest {


    /**
     * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory}
     *
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();



        context.register();
//
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
//
//        context.scan("com.louis.annotation");
//        context.refresh();
//        String uncapitalize = uncapitalize(AnnotationClass.class.getSimpleName());
//        AnnotationClass bean = context.getBean(uncapitalize, AnnotationClass.class);

//        DemoParam demoParam = new DemoParam();
//        System.out.println(demoParam == demoParam);

        System.out.println(9 % 10);
    }
}
