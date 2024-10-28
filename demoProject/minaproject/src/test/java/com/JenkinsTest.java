package com;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.job.Job;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.cdancy.jenkins.rest.features.JobsApi;
import com.cdancy.jenkins.rest.features.PluginManagerApi;
import com.cdancy.jenkins.rest.features.QueueApi;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.gitlab.api.GitlabAPI;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author louis
 * @date 2022/6/20
 * 1. 获取gitlab 仓库信息
 * 2. 配置 当前服务的git 仓库
 * 3. 拉取当前仓库的分支信息
 * 4. 将分支信息传给jenkins
 * ========
 * 2. 获取j job 列表（其实不需要，查看自己有权限项目，将项目与Jenkins job 关系做一个映射）
 * 3. 构建job 传参
 *
 *
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
    public static void viewList() {

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

        JobList dev = jobsApi.jobList("dev");
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
        Map<String, List<String>> param = Maps.newHashMap();
        param.put("branch", Lists.newArrayList("develop"));
        param.put("mvnCommand", Lists.newArrayList("mvn clean package -pl scrm-job -am -DskipTests=true -Pdev -U"));
        IntegerResponse integerResponse = api().buildWithParameters("", "dev-scrm-job", param);
        System.out.println(integerResponse);
    }

    @Test
    public void getGtiBranchList() {

    }
    public JenkinsClient jenkinsClient() {
        JenkinsClient client = JenkinsClient.builder()
                .endPoint("") // Optional. Defaults to http://127.0.0.1:8080
                .credentials("") // Optional.
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();

        log.info("jenkins client version: {}", systemInfo.jenkinsVersion());

        return client;
    }


}
