package com.louis.mybatis.dynamic.config;

import com.louis.mybatis.dynamic.base.ProviderSourceBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class MybatisConfig {
    private static SqlSessionFactory sessionFactory;

    public static SqlSessionFactory getSessionFactory() {
        ProviderSourceBuilder sqlSourceBuilder = new ProviderSourceBuilder();
        if (sessionFactory == null) {
            PooledDataSource dataSource = getDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
//            configuration.addMapper(UserMapper.class);
            sqlSourceBuilder.build(configuration);
            sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
        return sessionFactory;
    }

    private static PooledDataSource getDataSource() {
        PooledDataSource dataSource  = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_source?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("Root");
        return dataSource;
    }
}
