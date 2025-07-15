//package com.louis.minaProject.jpa.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
/// **
// * @author John·Louis
// * @date created on 2020/3/13
// * description:
// */
//@Configuration
//public class JpaMultiDataSourceConfig {
//
//
//    @Primary
//    @Bean("dataSourceOne")
//    @ConfigurationProperties(prefix = "spring.datasource.one")
//    public DataSource dataSourceOne() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean("dataSourceTwo")
//    @ConfigurationProperties(prefix = "spring.datasource.two")
//    public DataSource dataSourceTwo() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//}
