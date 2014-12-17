package com.iiovecherry.util.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author D.I.hunter
 * @ClassName: ProxyDto.java
 * @date 2014-12-17
 * @Description:The basic proxy DTO 
 */
public class ProxyDto {
    private Map<String,Boolean> setRecord=new HashMap<String,Boolean>();
    /**
     * Mark the field is changed
     * @param fieldName
     */
    public void markChange(String fieldName){
        if(StringUtils.isBlank(fieldName)){
            throw new NullPointerException();
        }
        setRecord.put(fieldName, true);
    }
    /**
     * check the field is change or not
     * @param fieldName
     * @return
     */
    public Boolean checkChange(String fieldName){
        Boolean res=false;
        if(StringUtils.isBlank(fieldName)){
            return res;
        }
        res=setRecord.get(fieldName);
        return res==null?false:res;
    }
    /**
     * clear all mark flag
     */
    public void clear(){
        setRecord.clear();
    }
}
