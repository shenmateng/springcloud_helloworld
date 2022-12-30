package com.mt.cnnotation;

import java.lang.annotation.*;

/**
 * @author 段杨宇
 * @create 2019-01-21 15:47
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JowtoResponseBody.List.class)
public @interface JowtoResponseBody {

    Class<?> type() default void.class;

    String[] include() default {};

    String[] exclude() default {};

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        JowtoResponseBody[] value();
    }
}
