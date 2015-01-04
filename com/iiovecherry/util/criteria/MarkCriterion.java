package com.iiovecherry.util.criteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author D.I.hunter
 * @ClassName: CriteriaMark.java
 * @date 2014-12-29
 * @Description:The mark for initial Criteria object with criterion
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface MarkCriterion {
    /**
     * search key
     * @return
     */
    String name() default "null";
    /**
     * the type for search model
     *  ex : eq like (like|start) (like|end) ge gt le lt in notin
     * @return
     */
    String type() default "eq";
    /**
     * the type for search data default is field value
     * ex: (date|yyyy-MM-dd HH:mm:ss|nnnn-nn-nn 23:59:59) int double float
     * @return
     */
    String typeData() default "null";
    /**
     * the mark for not search with data
     * @return
     */
    String noSearchMark() default "null";
    /**
     * the mark for set data with null
     * @return
     */
    String nullMark() default "empty";
}
