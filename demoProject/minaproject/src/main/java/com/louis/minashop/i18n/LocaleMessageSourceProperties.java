package com.louis.minashop.i18n;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;

/**
 * 添加配置默认的语言环境
 * default
 * @author jun.liu
 * @date 2020/5/22 13:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LocaleMessageSourceProperties extends MessageSourceProperties {

    private String defaultLocale;

    private Integer cookieMaxAge;

    private String cookieName;

}
