package com.louis.minashop.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author John·Louis
 * @date created on 2020/3/13
 * description:
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.louis.minashop.jpa.repository"
        ,entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanOne"
        ,transactionManagerRef ="platformTransactionManagerOne" )
public class JpaConfig {

    @Autowired
    @Qualifier(value = "dataSourceOne")
    DataSource dsOne;

    @Autowired
    JpaProperties jr;

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanOne(EntityManagerFactoryBuilder builder){
        return builder.dataSource(dsOne)
                .properties(jr.getProperties())
                .packages("com.louis.minashop.jpa.entity")
                .persistenceUnit("pu1")
                .build();

    }

    @Bean
    @Primary
    PlatformTransactionManager platformTransactionManagerOne(EntityManagerFactoryBuilder builder){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBeanOne(builder);
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
