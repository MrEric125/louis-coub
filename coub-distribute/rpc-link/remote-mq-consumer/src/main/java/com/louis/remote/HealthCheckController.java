package com.louis.remote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Value("${server.port:8080}")
    private String port;

    @Value("${spring.application.name:cluster-node}")
    private String appName;

    @Value("${node.id:unknown}")
    private String nodeId;


    @GetMapping("/health")
    public String health() {
        return "OK - " + appName + " - Node: " + nodeId;
    }
}
