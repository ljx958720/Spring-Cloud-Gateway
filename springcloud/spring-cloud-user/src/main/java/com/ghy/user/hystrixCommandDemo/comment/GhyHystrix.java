package com.ghy.user.hystrixCommandDemo.comment;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GhyHystrix {
    /**
     * 默认超时时间
     * @return
     */
    int timeout() default 1000;

    /**
     * 回退方法
     * @return
     */
    String fallback() default "";
}
