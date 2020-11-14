package com.luois;

import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author jun.liu
 * @date created on 2020/7/25
 * description:
 */
public class BeanUtilsTest {


    @Test
    public void test() {

        UserDto dto = new UserDto();
        dto.setAge(22);
        dto.setName("lisi");
        dto.setSource(true);
        UserDto dto1 = new UserDto();
        dto1.setAge(22);
        dto1.setName("wangwu");
        dto1.setSource(true);
        UserDto dto2 = new UserDto();
        dto2.setAge(22);
        dto2.setName("zhaoliu");
        dto2.setSource(true);
        UserDto dto3 = new UserDto();
        dto3.setAge(29);
        dto3.setName("zhaoliu");
        dto3.setSource(true);
        List<UserDto> list = Lists.newArrayList(dto, dto1, dto2, dto3);
        Comparator<UserDto> comparing = Comparator.comparing(UserDto::getAge);

        UserDto defaultDto = new UserDto();
        defaultDto.setName("");
        defaultDto.setAge(0);

        Map<String, UserDto> collect2 = list.stream().collect(Collectors.groupingBy(UserDto::getName, Collectors.reducing(defaultDto, BinaryOperator.maxBy(comparing))));
        Map<String, Optional<UserDto>> collect3 = list.stream()
                .collect(Collectors.groupingBy(UserDto::getName, Collectors.reducing( BinaryOperator.maxBy(comparing))));


        System.out.println(JSONObject.toJSONString(collect2, true));
        System.out.println(JSONObject.toJSONString(collect3, true));


    }
}
