package com.coub.louis.deploy;

import com.louis.common.common.HttpResult;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Louis
 * http://www.mydlq.club/article/23/
 * @date created on 2021/3/23
 * description:
 */
@RestController
@RequestMapping("/jenkins")
public class JenkinsController {


    @Autowired
    private JenkinsServer jenkinsServer;

    @Autowired
    private JenkinsHttpClient jenkinsHttpClient;


    @RequestMapping("/createJob")
    public HttpResult createJob(String jobName) throws IOException {

//        Resource resource = new ClassPathResource("jenkins_config.xml");
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        String s = bufferedReader.readLine();
//
//        while (s != null) {
//            stringBuilder.append(s).append("\n");
//            s = bufferedReader.readLine();
//        }
//
//        String jobXml = stringBuilder.toString();
//        bufferedReader.close();

        String script = "node(){ \n" +
                "echo 'hello world!' \n" +
                "}";
        // xml配置文件，且将脚本加入到配置中
        String xml = "<flow-definition plugin=\"workflow-job@2.32\">\n" +
                "<description>测试项目</description>\n" +
                "<definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.66\">\n" +
                "<script>" + script + "</script>\n" +
                "<sandbox>true</sandbox>\n" +
                "</definition>\n" +
                "</flow-definition>";


        jenkinsServer.createJob(jobName, xml);

        return HttpResult.ok();
    }
}
