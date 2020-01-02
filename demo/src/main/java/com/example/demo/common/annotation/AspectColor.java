package com.example.demo.common.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.PARAMETER})//该注解 只能在方法和参数处使用
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectColor {


  public String value() default "";


}
