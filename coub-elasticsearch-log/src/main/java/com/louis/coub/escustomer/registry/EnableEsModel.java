package com.louis.coub.escustomer.registry;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EsModelRegister.class})
public @interface EnableEsModel {

    String[] entityPath() default {};
}