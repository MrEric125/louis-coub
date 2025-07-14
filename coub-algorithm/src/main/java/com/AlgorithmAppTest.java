package com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 2025-7-1
 * java doc
 */
@SpringBootTest
public class AlgorithmAppTest {

    /**
     *
     * Tests the core pool size of the asynchronous executor.
     * <p>
     * This test verifies that the core pool size of the ThreadPoolTaskExecutor
     * returned by the asyncExecutor method of AlgorithmApp is set to 8.
     * </p>
     *
     */
    @Test
    public void testAsyncExecutorCorePoolSize() {
        AlgorithmApp algorithmApp = new AlgorithmApp();
        TaskExecutor executor = algorithmApp.asyncExecutor();
        assertTrue(executor instanceof ThreadPoolTaskExecutor);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) executor;
        assertEquals(8, threadPoolTaskExecutor.getCorePoolSize());
    }
}
