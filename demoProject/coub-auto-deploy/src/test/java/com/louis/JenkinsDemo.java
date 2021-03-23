package com.louis;


import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import org.junit.Test;

import java.net.URI;
import java.util.Map;

/**
 * @author Louis
 * @date created on 2021/3/19
 * @description:
 * @apiNote   https://github.com/jenkinsci/java-client-api
 *
 * @apiNote  https://github.com/cdancy/jenkins-rest
 * crumb
 */
public class JenkinsDemo {
    @Test
    public void test()  {
        try {
            JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localhost:8080"), "root", "Root");
            Map<String, Job> jobs = jenkinsServer.getJobs();

            jenkinsServer.createFolder("louis_coub");
            System.out.println(jobs);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
