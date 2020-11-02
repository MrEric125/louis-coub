package com.louis.mybatis.web;

import com.louis.common.common.HttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@RestController
public class MyController {


    @ResponseStatus(HttpStatus.CREATED)
    public HttpResult result() {
        return HttpResult.ok();

    }
}
