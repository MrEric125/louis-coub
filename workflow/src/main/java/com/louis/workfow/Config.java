package com.louis.workfow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jun.liu
 * @date created on 2020/11/15
 * description:
 */
@Configuration
public class Config {


    @Bean
    public ProcessEngineConfiguration standaloneProcessEngineConfiguration(){

        /** 使用代码创建工作流需要的23张表 */

            ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                    .createStandaloneProcessEngineConfiguration();
            // 连接数据库的配置
            processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
            processEngineConfiguration
                    .setJdbcUrl("jdbc:mysql://localhost:3306/workflow?useUnicode=true&characterEncoding=utf8");
            processEngineConfiguration.setJdbcUsername("root");
            processEngineConfiguration.setJdbcPassword("Root");

            /**
             * public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表存在
             * public static final String DB_SCHEMA_UPDATE_CREATE_DROP =
             * "create-drop";先删除表再创建表 public static final String DB_SCHEMA_UPDATE_TRUE =
             * "true";如果表不存在，自动创建表
             */
            processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
            // 工作流的核心对象，ProcessEnginee对象
            ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
            System.out.println("processEngine:" + processEngine);
        return processEngineConfiguration;
    }
}
