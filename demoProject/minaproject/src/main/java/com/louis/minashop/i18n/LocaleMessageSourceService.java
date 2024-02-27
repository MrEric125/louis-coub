package com.louis.minashop.i18n;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author lj125
 * @date 2020/5/22 14:40
 */
@Component
public class LocaleMessageSourceService {

    @Resource
    private MessageSource messageSource;

    @Autowired
    CookieLocaleResolver cookieLocaleResolver;

    /**
     * 无参数  无默认值
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code){
        return getMessage(code, null, null);
    }

    /**
     * 无参数，有默认值
     * @param code code ：对应messages配置的key.
     * @param defaultMessage 默认值
     * @return
     */
    public String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage);
    }

    /**
     * 有参数 无默认值
     *  @param code ：对应messages配置的key.
     *  @param args : 数组参数.占位符使用
     *  @return
     *     
     */
    public String getMessageWithOutDefault(String code, Object[] args) {
        return getMessage(code, args, null);
    }

    /**
     * 有参数 默认值为code
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.占位符使用
     * @return
     */
    public String getMessageCodeAsDefault(String code,Object[] args){
        return getMessage(code, args, code);
    }


     /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.占位符使用
     * @param defaultMessage : 没有设置key的时候的默认值.若从{@link MessageSource} 中未取到数据不会报错
     * @return 返回国际化配置中的数据
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Locale locale = cookieLocaleResolver.resolveLocale(request);
        return messageSource.getMessage(code, args, defaultMessage, locale);
     }

}
