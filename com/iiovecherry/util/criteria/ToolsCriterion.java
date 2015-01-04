package com.iiovecherry.util.criteria;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author D.I.hunter
 * @ClassName: CriterionTools.java
 * @date 2014-12-29
 * @Description:The tools for initial Criterion
 */
public class ToolsCriterion implements ICriteria {
    /**
     * the container for CriterionOC
     */
    private List<CriterionOC> list = new ArrayList<CriterionOC>();

    /**
     * The tools add criterion condition
     * 
     * @param criterion
     * @param name
     * @param value
     * @throws SearchException
     */
    public void addCriterion(MarkCriterion criterion, String name, String value) throws SearchException {
        list.add(new CriterionOC(criterion, name, value));
    }

    /**
     * The tools add criterion condition
     * 
     * @param criterion
     * @param name
     * @param value
     * @throws SearchException
     */
    public void addCriterion(MarkCriterion criterion, String name, List<String> value) throws SearchException {
        list.add(new CriterionOC(criterion, name, value));
    }

    @Override
    public void initCrieria(Criteria criteria) throws SearchException {
        for (CriterionOC item : list) {
            item.initCrieria(criteria);
        }
    }

    private class CriterionOC implements ICriteria,IValue {
        private MarkCriterion criterion;
        private String name;
        private boolean isMutile;
        private boolean isSearch;
        private boolean isNull;
        private String value;
        private List<String> values;

        private CriterionOC(MarkCriterion criterion, String name, String value) throws SearchException {
            this(criterion, name);
            this.value = value;
            this.isMutile = false;
            if ((this.value == null && "null".equalsIgnoreCase(criterion.noSearchMark())) || criterion.noSearchMark().equalsIgnoreCase(value)) {
                isSearch = false;
            } else {
                isSearch = true;
            }
            if ((this.value == null && "null".equalsIgnoreCase(criterion.nullMark())) || criterion.nullMark().equalsIgnoreCase(value)) {
                isNull = true;
            } else {
                isNull = false;
            }
        }

        private CriterionOC(MarkCriterion criterion, String name, List<String> values) throws SearchException {
            this(criterion, name);
            this.values = values;
            this.isMutile = true;
            if (this.values == null) {
                isSearch = false;
            } else {
                isSearch = true;
            }
            isNull = false;
        }

        private CriterionOC(MarkCriterion criterion, String name) throws SearchException {
            this.criterion = criterion;     
            if (criterion == null)
                throw new SearchException(Message.ANNO_IS_NULL);
            this.name = "null".equalsIgnoreCase(this.criterion.name())?name:this.criterion.name();
            if (this.name==null) {
                throw new SearchException(Message.NAME_NOT_FOUND);
            }
        }

        @Override
        public void initCrieria(Criteria criteria) throws SearchException {
            if (!isSearch)
                return;
            if (isMutile) {
                if ("in".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.in(name, getValues()));
                    return;
                }
                if ("notin".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.not(Restrictions.in(name, getValues())));
                    return;
                }
                throw new SearchException(Message.CONFIG_ERROR);
            } else {
                if (isNull) {
                    criteria.add(Restrictions.isNull(name));
                    return;
                }
                if ("eq".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.eq(name, getValue()));
                    return;
                }
                if ("le".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.le(name, getValue()));
                    return;
                }
                if ("ge".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.ge(name, getValue()));
                    return;
                }
                if ("like".equalsIgnoreCase(criterion.type()) || "like|any".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.like(name, (String) getValue(), MatchMode.ANYWHERE));
                    return;
                }
                if ("gt".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.gt(name, getValue()));
                    return;
                }
                if ("lt".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.lt(name, getValue()));
                    return;
                }
                if ("like|start".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.like(name, (String) getValue(), MatchMode.START));
                    return;
                }
                if ("like|end".equalsIgnoreCase(criterion.type())) {
                    criteria.add(Restrictions.like(name, (String) getValue(), MatchMode.END));
                    return;
                }
                throw new SearchException(Message.CONFIG_ERROR);
            }
        }

        @Override
        public Object getValue() throws SearchException {
            return getValue(value);
        }
        private Object getValue(String myValue) throws SearchException{
            if (myValue == null)
                return null;
            if (criterion.typeData().equalsIgnoreCase("null")) {
                return myValue;
            }
            ToolsTypeData tools = new ToolsTypeData(criterion.typeData());
            if (tools.getString(0) == null) {
                throw new SearchException(Message.CONFIG_ERROR);
            }
            if ("date".equalsIgnoreCase(tools.getString(0))) {
                try {
                    if (null == tools.getString(2)) {
                        return StringUtil.getDate(tools.getString(1), myValue);
                    } else {
                        return StringUtil.getDate(tools.getString(1), StringUtil.getFormateStr(tools.getString(2), myValue));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new SearchException(Message.DATA_PARAS_ERROR);
                }
            }
            if("int".equalsIgnoreCase(tools.getString(0))){
                if("true".equalsIgnoreCase(myValue)) return 0;
                if("false".equalsIgnoreCase(myValue)) return 1;
                return Integer.parseInt(myValue);
            }
            if("double".equalsIgnoreCase(tools.getString(0))){
                return Double.parseDouble(myValue);
            }
            if("float".equalsIgnoreCase(tools.getString(0))){
                return Float.parseFloat(myValue);
            }
            throw new SearchException(Message.DATA_PARAS_ERROR);
        }
        @Override
        public List<Object> getValues() throws SearchException {
            if(values==null) return null;
            List<Object> dataList=new ArrayList<Object>();
            for(String item:values){
                dataList.add(getValue(item));
            }
            return dataList;
        }
    }
}
