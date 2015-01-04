package com.iiovecherry.util.criteria;

import java.text.ParseException;
import java.util.List;

/**
 * 
 * @author D.I.hunter
 * @ClassName: IValue.java
 * @date 2014-12-29
 * @Description:The interface for get value
 */
public interface IValue {
    /**
     * The operator for get value
     * @return
     * @throws SearchException 
     * @throws ParseException 
     */
    public Object getValue() throws SearchException;
    /**
     * Get mutile value
     * @return
     * @throws SearchException 
     */
    public List<Object> getValues() throws SearchException;
}
