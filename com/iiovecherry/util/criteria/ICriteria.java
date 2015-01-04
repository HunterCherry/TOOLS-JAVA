package com.iiovecherry.util.criteria;

import org.hibernate.Criteria;

/**
 * 
 * @author D.I.hunter
 * @ClassName: ICriteria.java
 * @date 2014-12-29
 * @Description:The interface for initial criteria object
 */
public interface ICriteria {
    /**
     * the operator to intial criteria object
     * @param criteria
     */
    public void initCrieria(Criteria criteria) throws SearchException;
}
