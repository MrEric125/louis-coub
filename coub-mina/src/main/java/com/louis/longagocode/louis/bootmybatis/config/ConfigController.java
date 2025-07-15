package com.louis.longagocode.louis.bootmybatis.config;

import com.louis.longagocode.louis.bootmybatis.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller类
 */
@Order
@RestController
public class ConfigController {

    @Autowired
    private LouisBootProperties bootProperties;

    @Autowired(required = false)
    private BootLouisProperties bootLouisProperties;

    @GetMapping("/louis/boot")
    public ResponseData getProperties() {
        return new ResponseData<>(bootProperties);
    }

    @GetMapping("/boot/louis")
    public ResponseData getBootLouisProperties() {
        return new ResponseData<>(bootLouisProperties);

    }
}
