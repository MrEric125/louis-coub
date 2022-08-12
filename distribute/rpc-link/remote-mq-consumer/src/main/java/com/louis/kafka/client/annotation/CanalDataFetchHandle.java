package com.louis.kafka.client.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CanalDataFetchHandle {

    String tableName() default "";


}
