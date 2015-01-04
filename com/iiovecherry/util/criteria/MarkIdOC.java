package com.iiovecherry.util.criteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author D.I.hunter
 * @ClassName: MarkIdOC.java
 * @date 2014-12-29
 * @Description:mark OC object with id
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MarkIdOC {
    /**
     * defined id field name
     * @return
     */
    String id() default "id";
       
}
