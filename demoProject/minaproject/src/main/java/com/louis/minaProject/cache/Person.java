package com.louis.minaProject.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jun.liu
 * @date created on 2020/7/29
 * description:
 */
@Data
public class Person implements Serializable {

    private Long id;

    private String name;


}
