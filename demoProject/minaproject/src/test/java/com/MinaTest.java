package com;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Setter
@Getter
public class MinaTest {

    /**
     * name
     */
    private String name;

    private String age;

    public static void main(String[] args) {
        MinaTest test = new MinaTest();
        System.out.println(JSON.toJSONString(test, SerializerFeature.WriteNullStringAsEmpty));

    }
}
