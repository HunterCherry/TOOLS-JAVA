package com.iiovecherry.util.criteria;
/**
 * 
 * @author D.I.hunter
 * @ClassName: TypeTools.java
 * @date 2014-12-29
 * @Description:type tools to get List<String>
 */
public class ToolsTypeData {
    String[] data;
    public ToolsTypeData(String type) {
        super();
        if(type!=null){
            data = type.split("\\|"); 
        }      
    }
    public String getString(int index){
        if(data==null) return null;
        if(index>data.length-1){
            return null;
        }else{
            return data[index].trim();
        }
    }

}
