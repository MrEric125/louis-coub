package com.louis.minaProject.config;

import lombok.Data;

import java.util.List;

/**
 * @author jun.liu
 * @date created on 2020/10/31
 * description:
 */
@Data
public class LocalSecurity {

    private String username;

    private List<String> roles;
}
