package com.louis;

import com.google.common.collect.Sets;
import com.louis.annotation.AnnotationClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.springframework.util.StringUtils.uncapitalize;

public class SpringTest {


    /**
     * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory}
     *
     */
    @Test
    public void test() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
//
//        context.scan("com.louis.annotation");
//        context.refresh();
//        String uncapitalize = uncapitalize(AnnotationClass.class.getSimpleName());
//        AnnotationClass bean = context.getBean(uncapitalize, AnnotationClass.class);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(()->{
                aa();
            });
        }
    }

    private void aa() {
        synchronized (SynchronizedTest.class) {
            System.out.println(SynchronizedTest.aa);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
