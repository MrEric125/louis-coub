package com.exception;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author louis
 * <p>
 * Date: 2019/11/20
 * Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 保存数据可以使用异步的方式
     */
    @Autowired
    private TaskExecutor taskExecutor;

    @Value("${spring.profiles.active}")
    String profile;

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Wrapper accessDeniedException(AccessDeniedException e) {
        taskExecutor.execute(()->{
            log.error("accessDeniedException {}", e.getMessage(), e);
        });
        return WrapMapper.error("当前用户无权限");

    }

}

