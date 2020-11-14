package com.louis.provider.impl;

import com.louis.provider.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Component
public class AuthorizeConfigProviderImpl implements AuthorizeConfigProvider {


    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                .anyRequest()
                .access("@permissionService.hasPermission(authentication,request)");
        return true;
    }
}
