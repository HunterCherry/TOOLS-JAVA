package com.iiovecherry.util.criteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;

/**
 * 
 * @author D.I.hunter
 * @ClassName: InitCrieriaBase.java
 * @date 2014-12-29
 * @Description:The tools initial criteria
 */
public class InitCrieriaBase implements ICriteria,IFieldListen{
    private Object obj;  
    private ToolsCriterion tCriterion;
    private ToolsOrder toolsOrder;
    public InitCrieriaBase(Object obj) {
        super();
        this.obj = obj;
        this.tCriterion=new ToolsCriterion();
        this.toolsOrder=new ToolsOrder();
    }

    @Override
    public void initCrieria(Criteria criteria) throws SearchException {
        if(obj==null||criteria==null) return ;
        ForeachField tools=new ForeachField(obj, this);
        tools.start();
        tCriterion.initCrieria(criteria);
        toolsOrder.initCrieria(criteria);
    }
    @SuppressWarnings({"rawtypes" })
    @Override
    public void fieldOne(Field field, Object value) throws SearchException {
        MarkOrder order = field.getAnnotation(MarkOrder.class);
        if(order!=null){
            toolsOrder.addOrder(order, field.getName());
        }
        MarkCriterion criterion=field.getAnnotation(MarkCriterion.class);
        if(criterion!=null){
            if(value instanceof Collection){
                tCriterion.addCriterion(criterion, field.getName(), getStrings(((Collection)value)));
            }else{
                tCriterion.addCriterion(criterion, field.getName(), getString(value));
            }
           
        }
    }
    @SuppressWarnings("rawtypes")
    private List<String> getStrings(Collection values) throws SearchException{
        if(values==null) return null;
        List<String> list=new ArrayList<String>();
        for(Object item:values){
            list.add(getString(item));
        }
        return list;
    }
    private String getString(Object value) throws SearchException{
        if(value==null) return null;
        if(value instanceof String) return (String) value;
        if(value instanceof Date){
            Date date=(Date) value;
            return StringUtil.getDateStr(null, date);          
        }
        ToolsIdOC idOC=new ToolsIdOC(value.getClass());
        if(idOC.isIdOC()){
            return idOC.getId(value);
        }
        return value.toString();
    }
    
}
