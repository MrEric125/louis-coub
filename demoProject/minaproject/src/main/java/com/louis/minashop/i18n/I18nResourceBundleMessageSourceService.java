package com.louis.minashop.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;

/**
 *
 * @author jun.liu
 * @date 2020/5/21 17:29
 * <p>
 * {@link ResourceBundleMessageSource}
 */
@Service
public class I18nResourceBundleMessageSourceService extends ResourceBundleMessageSource {



    /**
     * 重写该方法，便于以后扩展获取自定义参数
     *当 没有参数的时候调用的方法
     * @param code
     * @param locale
     * @return
     */
    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String withoutArguments = super.resolveCodeWithoutArguments(code, locale);
//        if (withoutArguments == null) {
            //todo 这里可以依赖国家化服务
//            return "no result";
//        }
        return withoutArguments;

    }

    /**
     * 重写该方法，便于以后扩展获取自定义参数
     * 当有参数的时候的方法
     * @param code
     * @param locale
     * @return
     */
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MessageFormat messageFormat = super.resolveCode(code, locale);
//        if (messageFormat == null) {
        // todo 这个地方可以从国际化服务取值，或者首先从国际化取值，如果没有再执行resolveCode
//            return new MessageFormat(code+"formatttt");
//        }
        return messageFormat;
    }
}
