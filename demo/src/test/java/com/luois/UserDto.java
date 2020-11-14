package com.luois;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author jun.liu
 * @date created on 2020/7/25
 * description:
 */
@Data
@ToString
public class UserDto implements Serializable {

    private String name;

    private Integer age;

    private Boolean source;
}
