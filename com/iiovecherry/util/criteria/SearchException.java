package com.iiovecherry.util.criteria;
/**
 * 
 * @author D.I.hunter
 * @ClassName: OCException.java
 * @date 2014-12-29
 * @Description:The exception for OC package
 */
public class SearchException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -2206579198266616785L;
    private static String OC_MARK="【OC change exception】";
    private String message;   
    public SearchException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return OC_MARK+message;
    }

}
