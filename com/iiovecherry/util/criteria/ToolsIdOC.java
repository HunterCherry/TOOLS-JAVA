package com.iiovecherry.util.criteria;

import java.lang.reflect.Field;

/**
 * 
 * @author D.I.hunter
 * @ClassName: IdOCTools.java
 * @date 2014-12-29
 * @Description:IdOC tools
 */
public class ToolsIdOC {
    private MarkIdOC anno;
    private Class<?> myClass;
    private int dept=2;
    private Field idField;
    public ToolsIdOC(Class<?> myClass){
        super();
        anno=myClass.getAnnotation(MarkIdOC.class);
        this.myClass=myClass;
    } 
    /**
     * Check class is IdOC or not
     * @return
     */
    public boolean isIdOC(){
        initAnno(myClass, dept);
        return anno==null?false:true;
    }
    /**
     * Get object id if find the id value
     * @param ob
     * @return
     * @throws SearchException
     */
    public String getId(Object ob) throws SearchException{
        if(!isIdOC())  throw new SearchException(Message.ANNO_IS_NULL);
        try {
            ToolsField tools=new ToolsField();
            tools.setDept(dept);
            idField=tools.getField(myClass, anno.id());
            if(idField==null) throw new SearchException(Message.ID_NOT_FOUND);
            idField.setAccessible(true);
            Object obStr=idField.get(ob);
            if(obStr==null) return null;
            if(obStr instanceof String){
                return (String)obStr;
            }
            throw new SearchException(Message.ID_NOT_STRING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SearchException(e.getMessage());
        }
    }
    /**
     * To find annotation 
     * @param myClass
     * @param level
     */
    private void initAnno(Class<?> myClass,int level){
        if(anno!=null||myClass==null||level<1) return;
        anno=myClass.getAnnotation(MarkIdOC.class);
        initAnno(myClass.getSuperclass(),level-1);
    }   
}
