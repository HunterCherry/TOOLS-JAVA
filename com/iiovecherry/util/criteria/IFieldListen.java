package com.iiovecherry.util.criteria;

import java.lang.reflect.Field;

/**
 * 
 * @author D.I.hunter
 * @ClassName: IField.java
 * @date 2014-12-30
 * @Description:foreach field in object
 */
public interface IFieldListen {
    /**
     * The callback when find one field
     * @param field
     * @param obj
     * @throws SearchException 
     */
    public void fieldOne(Field field,Object value) throws SearchException;
}
