package com.louis;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {


    /**
     * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory}
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
