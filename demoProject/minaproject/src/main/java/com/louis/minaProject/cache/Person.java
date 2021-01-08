package com.louis.minaProject.cache;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jun.liu
 * @date created on 2020/7/29
 * description:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable {

    private Long id;

    private String name;

//    private Integer age;


}
