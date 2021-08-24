package louis.coub.escustomer.registry;

import java.lang.annotation.*;

/**
 * @author jun.liu
 * @since 2021/2/4 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface EsModel {

    String index();

    String template();

    String alias() default "";

    String type() ;
}
