package com.louis.minaProject.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jun.liu
 * @date created on 2020/7/4
 * description:
 */
@Configuration
public class SparkConfig {

    @Bean
     public SparkConf conf() {
        return new SparkConf().setMaster("local")
                .setAppName("HelloSpark");
    }
    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(conf());
    }
//    @Bean
//    public SparkContext sparkContext() {
//        return new SparkContext(conf());
//    }
//    @Bean
//    public SparkContext context() {
//        return new SparkContext(conf());
//    }



}
