package com.iiovecherry.util.criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 * 
 * @author D.I.hunter
 * @ClassName: OrderTools.java
 * @date 2014-12-29
 * @Description:the tools for initial order
 */
public class ToolsOrder implements ICriteria {
    /**
     * The container for CriteriaOrder
     */
    private List<OrderOC> list = new ArrayList<OrderOC>();
    /**
     * The tools for order list
     */
    private Comparator<OrderOC> comparator = new Comparator<OrderOC>() {
        @Override
        public int compare(OrderOC o1, OrderOC o2) {
            return o1.level() - o2.level();
        }
    };

    /**
     * Add a order mark
     * 
     * @param mark
     * @throws SearchException 
     */
    public void addOrder(MarkOrder mark, String name) throws SearchException {
        list.add(new OrderOC(mark, name));
    }
    @Override
    public void initCrieria(Criteria criteria) {
        Collections.sort(list, comparator);
        for (OrderOC item : list) {
           item.initCrieria(criteria);
        }       
    }
    private class OrderOC implements ICriteria {
        private MarkOrder order;
        private String name;

        public OrderOC(MarkOrder order, String name) throws SearchException {
            super();
            this.order = order;
            if (this.order == null) {
                throw new SearchException("order can't be null");
            }
            this.name = "null".equalsIgnoreCase(order.name())?name:order.name();
            if (this.name==null) {
                throw new SearchException("order can't find name");
            }
                
        }
        private int level() {
            return order.level();
        }
        @Override
        public void initCrieria(Criteria criteria) {
            if(order.isAsc()){
                criteria.addOrder(Order.asc(name));
            }else{
                criteria.addOrder(Order.desc(name));
            }
            
        }
    }


}
