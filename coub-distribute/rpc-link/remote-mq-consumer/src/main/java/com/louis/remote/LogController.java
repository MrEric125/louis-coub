package com.louis.remote;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private AdminClient adminClient;

    @GetMapping("/log/{path}")
    public String log(@PathVariable String path) {
        log.info("this is info{}",path);
        log.error("this is error{}",path);
        log.debug("this is debug{}",path);
        log.warn("this is warn{}",path);
        return path;
    }

    @GetMapping("/topicList")
    public Set<String> topicList() throws ExecutionException, InterruptedException {
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        return listTopicsResult.names().get();
    }
}
