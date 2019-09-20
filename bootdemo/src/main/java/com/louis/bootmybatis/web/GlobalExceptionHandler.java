package com.louis.bootmybatis.web;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author louis
 * <p>
 * Date: 2019/7/5
 * Description:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


//    /**
//     *
//     * @param request
//     * @param response
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Wrapper exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
//        log.info("错误处理器：{}", e.getMessage());
//        e.printStackTrace();
//        response.setStatus(HttpStatus.BAD_REQUEST.value());
//        return WrapMapper.error(e.getMessage());
//    }

}
