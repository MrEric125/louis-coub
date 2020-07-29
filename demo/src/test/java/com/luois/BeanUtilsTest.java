package com.luois;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

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
        dto.setName("zhangsan");
        dto.setSource(true);

        UserInfo info = new UserInfo();
        UserInfo user = new UserInfo();

        try {

            BeanUtils.copyProperties(dto, info);


        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println(info);



    }
}
