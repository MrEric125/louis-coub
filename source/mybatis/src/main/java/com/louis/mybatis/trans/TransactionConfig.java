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
 * 1. 查看表是否支持事务(MYISAM ) 不支持事务的
 * 3. 代码中是否开启事务，如果没有开启事务，那么不会滚
 * 2. 查看异常是否被抛出，如果在事务方法内，将异常处理了，那么就不回滚
 * 3. 查看抛出的异常是否为事务方法支持的异常，默认支持runtimeException
 *
 *
 *
 */

public class TransactionConfig {
}
