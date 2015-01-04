package com.iiovecherry.util.criteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author D.I.hunter
 * @ClassName: CriteriaOrder.java
 * @date 2014-12-29
 * @Description:The mark for initial Criteria object with order
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface MarkOrder {
    /**
     * if default value means order by field name
     * @return
     */
    String name() default "null";
    /**
     * defined order type default is ASC
     * @return
     */
    boolean isAsc() default true;
    /**
     * set order of oder by
     * @return
     */
    int level() default 0;
}
