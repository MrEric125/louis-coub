//package com.config;
//
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author John·Louis
// * @date create in 2019/9/28
// * description:
// */
//@Configuration
//public class ActivitiConfig {
//
//    CommandLineRunner init(final RepositoryService repositoryService,
//                           final RuntimeService runtimeService,
//                           final TaskService taskService) {
//        return args -> {
//            Map<String, Object> variables = new HashMap<>();
//            variables.put("applicantName", "John Doe");
//            variables.put("email", "john.doe@activiti.com");
//            variables.put("phoneNumber", "123456789");
//            runtimeService.startProcessInstanceByKey("hireProcess", variables);
//        };
//
//    }
//}
