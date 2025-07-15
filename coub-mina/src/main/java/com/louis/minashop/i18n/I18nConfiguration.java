package com.louis.minashop.i18n;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.time.Duration;
import java.util.Locale;

/**
 * @author jun.liu
 * @date 2020/5/21 15:06
 */
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties()
@ConditionalOnProperty(name = "i18n", prefix = "louis", havingValue = "true")
public class I18nConfiguration {

    static {
        System.out.println("init");
    }

    @Bean()
    @Conditional(I18nConfiguration.ResourceBundleCondition.class)
    public MessageSource messageSource(MessageSourceProperties properties) {
        I18nResourceBundleMessageSourceService messageSource = new I18nResourceBundleMessageSourceService();
        if (StringUtils.hasText(properties.getBasename())) {
            messageSource.setBasenames(StringUtils
                    .commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
        }
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;

    }

    @Bean
    @Conditional(I18nConfiguration.ResourceBundleCondition.class)
    public CookieLocaleResolver cookieLocaleResolver(LocaleMessageSourceProperties properties) {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        String defaultLocale = properties.getDefaultLocale();
        Locale locale;
        if (StringUtils.hasText(defaultLocale)) {
            locale = new Locale(defaultLocale);
        } else {
            locale = LocaleContextHolder.getLocale();
        }
        cookieLocaleResolver.setDefaultLocale(locale);
        Integer cookieMaxAge = properties.getCookieMaxAge();
        if (cookieMaxAge != null) {
            cookieLocaleResolver.setCookieMaxAge(cookieMaxAge);
        }
        String cookieName = properties.getCookieName();
//        Locale.
        // 如果没有设置那就使用默认的值 org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE
        if (StringUtils.hasText(cookieName)) {

            cookieLocaleResolver.setCookieName(cookieName);
        }
        return cookieLocaleResolver;
    }


    private static final Resource[] NO_RESOURCES = {};

    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public LocaleMessageSourceProperties messageSourceProperties() {
        return new LocaleMessageSourceProperties();
    }

    protected static class ResourceBundleCondition extends SpringBootCondition {

        private static ConcurrentReferenceHashMap<String, ConditionOutcome> cache = new ConcurrentReferenceHashMap<>();

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String basename = context.getEnvironment().getProperty("spring.messages.basename", "messages");
            ConditionOutcome outcome = cache.get(basename);
            if (outcome == null) {
                outcome = getMatchOutcomeForBasename(context, basename);
                cache.put(basename, outcome);
            }
            return outcome;
        }

        private ConditionOutcome getMatchOutcomeForBasename(ConditionContext context, String basename) {
            ConditionMessage.Builder message = ConditionMessage.forCondition("ResourceBundle");
            for (String name : StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(basename))) {
                for (Resource resource : getResources(context.getClassLoader(), name)) {
                    if (resource.exists()) {
                        return ConditionOutcome.match(message.found("bundle").items(resource));
                    }
                }
            }
            return ConditionOutcome.noMatch(message.didNotFind("bundle with basename " + basename).atAll());
        }

        private Resource[] getResources(ClassLoader classLoader, String name) {
            String target = name.replace('.', '/');
            try {
                return new PathMatchingResourcePatternResolver(classLoader)
                        .getResources("classpath*:" + target + ".properties");
            } catch (Exception ex) {
                return NO_RESOURCES;
            }
        }
    }
}
