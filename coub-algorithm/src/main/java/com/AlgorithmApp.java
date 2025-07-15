package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * 讲解： https://www.cnblogs.com/onepixel/p/7674659.html
 * 视图演示：https://visualgo.net/zh/
 */
@Slf4j
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.web")
public class AlgorithmApp extends Pub {

    public static void main(String[] args) {
        SpringApplication.run(AlgorithmApp.class, args);
    }

    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        tt();
//        输出"hello world"


    }

    /**
     * Creates and configures a {@link TaskExecutor} for asynchronous task execution.
     *
     * <p>This method sets up a {@link ThreadPoolTaskExecutor} with specific configurations
     * such as core pool size, maximum pool size, queue capacity, and thread name prefix.
     * It also defines the behavior for handling tasks when the pool is at maximum capacity
     * and ensures that all tasks are completed before shutting down the executor.</p>
     *
     * @return a configured {@link TaskExecutor} instance for handling asynchronous tasks.
     */
    @Bean
    public TaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(8);
        // 设置最大线程数
        executor.setMaxPoolSize(16);
        // 设置队列容量
        executor.setQueueCapacity(20);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("algo-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}
