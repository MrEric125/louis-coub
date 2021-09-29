package com.louis.mybatis.trans;


import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Method;

/**
 * xml 配置
 * {@link  org.springframework.transaction.config.TxNamespaceHandler}
 *
 * config code 模式
 * {@link DataSourceTransactionManagerAutoConfiguration}
 *
 * {@link TransactionManagerCustomizers}
 *
 * {@link TransactionTemplate}
 *
 * most important base class
 * {@link org.springframework.transaction.support.AbstractPlatformTransactionManager}
 *
 * {@link TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)}
 *
 * {@link org.springframework.jdbc.datasource.DataSourceTransactionManager}
 *
 */

public class TransactionConfig {
}
