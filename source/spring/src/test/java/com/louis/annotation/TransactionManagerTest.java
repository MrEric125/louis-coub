package com.louis.annotation;

import com.zaxxer.hikari.util.DriverDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionManagerTest {

    private DataSource dataSource;

    @Before
    public void before() {
        dataSource = new DriverDataSource(null, null, null, null, null);
    }


    /**
     * 原生的事务写法
     * orm 框架  绑定参数，生成sql,执行sql pojo 结果映射，这种统一的事务管理机制原始很难支持，
     * 就需要spring 事务支持
     *
     * @throws SQLException
     */
    @Test
    public void test1() throws SQLException {
        Connection connection = dataSource.getConnection();
//        设置自动提交为false
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        try {
            statement.execute("delete from tb_user where user_id=100");
            statement.execute("select * from tb_user where user_id=100");
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }

    }


    /**
     * 编程式的事务方式
     */
    @Test
    public void test2() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 不同的ORM 框架这里使用不同的TransactionManager就可以了
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            dataSourceTransactionManager.commit(transaction);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transaction);

        }
    }

    /**
     * 申明式事务
     * 配置，注解
     */
    @Test
    @Transactional
    public void test3() {

    }
}
