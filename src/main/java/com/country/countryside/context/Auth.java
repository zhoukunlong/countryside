package com.country.countryside.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加上此注解，代表会进行登录验证（包括授权检测）
 * @author zhoukunlong
 *
 * @date 2023/01/16
 */
@Target({ElementType.TYPE, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Auth {

}
