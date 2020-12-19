package com.example.pro.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yushansun
 * @title: Aspect
 * @projectName leetcode
 * @description: TODO
 * @date 2020/12/82:02 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 包名
     */
    String pkg() default "";
    /**
     * 类名
     */
    String cls() default "";
}
