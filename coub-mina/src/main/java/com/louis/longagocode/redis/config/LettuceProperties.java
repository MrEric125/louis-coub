package com.louis.longagocode.redis.config;

import com.redis.config.LettuceClusterProperties;
import com.redis.config.LettuceSentinelProperties;
import com.redis.config.LettuceSingleProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jun.liu
 * @date 2020/8/14 10:50
 */
@Data
@ConfigurationProperties(prefix = "lettuce")
public class LettuceProperties  {

    private LettuceSingleProperties single;
    private LettuceReplicaProperties replica;
    private LettuceSentinelProperties sentinel;
    private LettuceClusterProperties cluster;


}
