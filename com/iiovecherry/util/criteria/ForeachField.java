package com.iiovecherry.util.criteria;

import java.lang.reflect.Field;

/**
 * 
 * @author D.I.hunter
 * @ClassName: ForeachField.java
 * @date 2014-12-30
 * @Description:The tools to for each field
 */
public class ForeachField {
    private IFieldListen iField;
    private int level=2;
    private Object object;
    
    public ForeachField(Object object) throws SearchException {
        super();
        if(object==null) throw new SearchException(Message.DATA_IS_NULL);
        this.object = object;
    }
    

    public ForeachField( Object object,IFieldListen iField) throws SearchException {
        this(object);
        this.iField = iField;
    }


    /**
     * The way to start for each object
     * @throws SearchException 
     */
    public void start() throws SearchException{
        try {
            foreach(object.getClass(), level);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SearchException(e.getMessage());
        } 
    }
    
    private void foreach(Class<?> myClass,int level) throws IllegalArgumentException, IllegalAccessException, SearchException{
        if(myClass==null||level<1) return;
        Field[] fields = myClass.getDeclaredFields();
        if(fields!=null&&fields.length>0&&iField!=null){
            for(Field item:fields){
                item.setAccessible(true);
                iField.fieldOne(item, item.get(object));
            }
        }
        foreach(myClass.getSuperclass(), level-1);
    }
    
    
    public IFieldListen getiField() {
        return iField;
    }

    public void setiField(IFieldListen iField) {
        this.iField = iField;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
}
