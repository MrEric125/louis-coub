package com.louis;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.liu
 * @since 2021/1/11 9:29
 */
@Slf4j
@RestController
public class TestHttpClient {

    @RequestMapping("/httpClientPool")
    public HttpResult httpClientPool(@RequestParam String url, @RequestParam String method) throws Exception {
        String result = null;
        if (StringUtils.isNotBlank(method) && StringUtils.equals(method, "POST")) {
            result= HttpClientUtils.doGet(url, null);
//            result=HttpClient2.getHttpClient(url);
        } else {
            result= HttpClientUtils.doGet(url, null);
            log.info(result);

        }
        return HttpResult.ok(result);

    }

    @RequestMapping("/httpClient")
    public HttpResult httpClient(@RequestParam String url, @RequestParam String method,@RequestBody(required = false) String data) throws Exception {
        String result = null;
        if (StringUtils.isNotBlank(method) && StringUtils.equals(method, "POST")) {
            if (StringUtils.isNotBlank(data)) {
//                result = HttpUtils.httpsPost(url, data);
//                result = HttpClientUtils.doPostJson(url, data, null);
                result = HttpClientUtils.doPost2(url, data);
            }
        } else {
            result = HttpClientUtils.doGet(url, null);
        }
        log.info(result);
        return HttpResult.ok(result);

    }

    public static void main(String[] args) {
        String var="{\"code\":200,\"message\":\"操作成功\",\"result\":\"Hello World\"}";
        System.out.println(JSON.toJSONString(var, true));
    }
}
