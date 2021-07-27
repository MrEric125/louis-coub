package com.security;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author louis
 * <p>
 * Date: 2019/12/2
 * Description:
 */

public class SecurityTest {

    @Test
    public void test() {
        Person request = new Person();
        request.setAge("11");
        request.setName("zhangsan");
        String string = JSONObject.toJSONString(request);
        System.out.println(request);
        System.out.println("format:  "+ string);
    }
}

