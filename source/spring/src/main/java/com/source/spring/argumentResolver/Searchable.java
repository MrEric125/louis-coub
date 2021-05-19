package com.source.spring.argumentResolver;

import java.lang.annotation.*;

/**
 * @author Louis
 * @date created on 2021/1/18
 * description:
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Searchable {
}
