package com.louis.objectcontainer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import swiftsdk.SfOssClient;
import swiftsdk.SwiftConfiguration;
import swiftsdk.util.TokenCache;

/**
 * @author louis
 * <p>
 * Date: 2019/8/12
 * Description:
 */

@Configuration
public class SwiftConfig {

    @Value("${swift.config.account}")
    private String account;


    @Value("${swift.config.userKey}")
    private String userKey;


    @Value("${swift.config.serverUrl}")
    private String ossServerUrl;

    @Bean
    public SwiftConfiguration configuration() {
        SwiftConfiguration configuration = new SwiftConfiguration();
        configuration.setAccount(account);
        configuration.setUserName(account);
        configuration.setUserKey(userKey);
        configuration.setSfossServerUrl(ossServerUrl);
        return configuration;
    }

    @Bean
    public TokenCache tokenCache() {
        return new TokenCache(configuration());
    }


    @Bean
    public SfOssClient sfOssClient() {
        SfOssClient sfOssClient = new SfOssClient(configuration());
        sfOssClient.setV2tokne(tokenCache().getToken());
        return sfOssClient;
    }
}
