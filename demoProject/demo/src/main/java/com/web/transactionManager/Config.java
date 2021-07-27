package com.web.transactionManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author John·Louis
 * @date created on 2020/3/18
 * description:
 */
@Configuration
@EnableTransactionManagement
public class Config {

//    @Bean
//    DataSource dataSource() {
//        return new DruidDataSource();
//
//    }
//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
