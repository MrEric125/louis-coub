package com.web.transactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author John·Louis
 * @date created on 2020/3/18
 * description:
 */
@Configuration
@EnableTransactionManagement
public class Config {

    @Bean
    DataSource dataSource() {
        return new DruidDataSource();

    }


    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
