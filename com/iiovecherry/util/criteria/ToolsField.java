package com.iiovecherry.util.criteria;

import java.lang.reflect.Field;

/**
 * 
 * @author D.I.hunter
 * @ClassName: ToolsGetField.java
 * @date 2014-12-30
 * @Description:Get field by name
 */
public class ToolsField {
    private Field field;
    private String name;
    private int dept = 2;

    public Field getField(Class<?> myClass, String name, int dept) throws SearchException {
        this.dept = dept;
        return getField(myClass, name);
    }

    public Field getField(Class<?> myClass, String name) throws SearchException {
        if (StringUtil.isBlank(name) || myClass == null)
            throw new SearchException(Message.DATA_IS_NULL);
        this.name = name;
        initField(myClass, dept);
        return field;
    }

    /**
     * To find id field
     * 
     * @param myClass
     * @param level
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    private void initField(Class<?> myClass, int level) {
        if (field != null || myClass == null || level < 1)
            return;
        try {
            field = myClass.getDeclaredField(name);
        } catch (Exception e) {
            field = null;
        }
        initField(myClass.getSuperclass(), level - 1);
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

}
