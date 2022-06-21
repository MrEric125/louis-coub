package com;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.job.Job;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.plugins.Plugins;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.cdancy.jenkins.rest.features.JobsApi;
import com.cdancy.jenkins.rest.features.PluginManagerApi;
import com.cdancy.jenkins.rest.features.QueueApi;
import lombok.extern.slf4j.Slf4j;
import org.gitlab.api.GitlabAPI;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * @author louis
 * @date 2022/6/20
 */
@Slf4j
public class JenkinsTest {

    public GitlabAPI buildGitlabApi() {
        return GitlabAPI.connect("", "");
    }


    public static JobsApi api() {
        JenkinsTest test = new JenkinsTest();
        JenkinsClient jenkinsClient = test.jenkinsClient();

        return jenkinsClient.api().jobsApi();
    }

    public static PluginManagerApi pluginManagerApi() {
        JenkinsTest test = new JenkinsTest();
        JenkinsClient jenkinsClient = test.jenkinsClient();

        return jenkinsClient.api().pluginManagerApi();
    }
    public static QueueApi queueApi() {
        JenkinsTest test = new JenkinsTest();
        JenkinsClient jenkinsClient = test.jenkinsClient();

       return jenkinsClient.api().queueApi();
    }


    @Test
    public void jenkinsClientTest() {
        JenkinsTest test = new JenkinsTest();
        test.jenkinsClient();
    }

    @Test
    public void listJob() {

        JenkinsTest test = new JenkinsTest();
        JenkinsClient jenkinsClient = test.jenkinsClient();

        JobsApi jobsApi = jenkinsClient.api().jobsApi();

        JobList dev = jobsApi.jobList("");
        if (Objects.nonNull(dev)) {
            List<Job> jobs = dev.jobs();

            for (Job job : jobs) {
                log.info("job:name: {}; color:{}; url:{}", job.name(), job.color(), job.url());
            }
        }
    }
    @Test
    public void jobInfo() {
        JenkinsTest test = new JenkinsTest();
        JenkinsClient jenkinsClient = test.jenkinsClient();

        JobsApi jobsApi = jenkinsClient.api().jobsApi();

        JobInfo jobInfo = jobsApi.jobInfo(null, "dev-crm-manager");

        if (Objects.nonNull(jobInfo)) {
            log.info("jobInfo: {},{},{}", jobInfo.buildable(), jobInfo.color(), jobInfo.description());
        }
    }

    @Test
    public void buildJob() {
        Plugins plugins = pluginManagerApi().plugins(3, null);
        System.out.println(plugins);
    }

    @Test
    public void getGtiBranchList() {

    }
    public JenkinsClient jenkinsClient() {
        JenkinsClient client = JenkinsClient.builder()
                .endPoint("") // Optional. Defaults to http://127.0.0.1:8080
                .credentials("sdf") // Optional.
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();

        log.info("jenkins client version: {}", systemInfo.jenkinsVersion());

        return client;
    }


}
