package com.icss.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface SysControllerLog {//自定义注释
	 /**
     * 描述控制层业操作 例:Xxx管理
     * @return
     */
    String requestoper()  default ""; 
    String permid() default "";
    
}
