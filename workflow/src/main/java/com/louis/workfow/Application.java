package com.louis.workfow;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.process.runtime.connector.Connector;
import org.activiti.api.runtime.shared.query.Pageable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author jun.liu
 * @since 2020/9/17 10:41
 */
@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {

    private ProcessRuntime processRuntime;

    public Application(ProcessRuntime processRuntime) {
        this.processRuntime = processRuntime;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @PostMapping("/documents")
    public String processFile(@RequestBody String content) {

        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("categorizeProcess")
                .withVariable("fileContent",
                        content)
                .build());
        String message = ">>> Created Process Instance: " + processInstance;
        System.out.println(message);
        return message;
    }

    @GetMapping("/process-definitions")
    public List<ProcessDefinition> getProcessDefinition(){
        return processRuntime.processDefinitions(Pageable.of(0, 100)).getContent();
    }

    @Override
    public void run(String... args) {
    }

    @Bean
    public Connector processTextConnector() {
        return integrationContext -> {
            Map<String, Object> inBoundVariables = integrationContext.getInBoundVariables();
            String contentToProcess = (String) inBoundVariables.get("fileContent");
            // Logic Here to decide if content is approved or not
            if (contentToProcess.contains("activiti")) {
                integrationContext.addOutBoundVariable("approved",
                        true);
            } else {
                integrationContext.addOutBoundVariable("approved",
                        false);
            }
            return integrationContext;
        };
    }

    @Bean
    public Connector tagTextConnector() {
        return integrationContext -> {
            String contentToTag = (String) integrationContext.getInBoundVariables().get("fileContent");
            contentToTag += " :) ";
            integrationContext.addOutBoundVariable("fileContent",
                    contentToTag);
            System.out.println("Final Content: " + contentToTag);
            return integrationContext;
        };
    }

    @Bean
    public Connector discardTextConnector() {
        return integrationContext -> {
            String contentToDiscard = (String) integrationContext.getInBoundVariables().get("fileContent");
            contentToDiscard += " :( ";
            integrationContext.addOutBoundVariable("fileContent",
                    contentToDiscard);
            System.out.println("Final Content: " + contentToDiscard);
            return integrationContext;
        };
    }

}
