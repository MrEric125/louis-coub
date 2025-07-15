package com.louis.minaProject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author jun.liu
 * @date created on 2020/10/31
 * description:
 */
@Validated
@Data
@Component
@ConfigurationProperties(prefix = "acme")
public class AcmeProperties {

    private String remoteAddress;

    private LocalSecurity security;


}
