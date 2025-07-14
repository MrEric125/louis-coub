package com.louis.longagocode.redis.config;

import com.redis.config.LettuceSingleProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jun.liu
 * @date 2020/8/14 10:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LettuceSentinelProperties  extends LettuceSingleProperties {
    private String masterId;

}
