package com.louis.mybatis.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Config {

    private String host;

    private Integer port;

    private String username;

    private String password;

}
