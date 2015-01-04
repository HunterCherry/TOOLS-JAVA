package com.iiovecherry.util.criteria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author D.I.hunter
 * @ClassName: StringUtil.java
 * @date 2014-12-29
 * @Description:String hander tools
 */
class StringUtil {
    /**
     * Model date with date have time
     */
    public static final String DATE_TIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * check the String is blank or not
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null)
            return false;
        return StringUtils.isBlank(str);
    }

    /**
     * Get new Date String (replace char 'n')
     * 
     * @param formate
     *            nnnn-nn-nn 23:59:59
     * @param dateStr
     *            2014-12-12 13
     * @return
     */
    public static String getFormateStr(String formate, String dateStr) {
        if (formate == null)
            return null;
        char[] chars = formate.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if ('n' == chars[i]) {
                chars[i] = dateStr.charAt(i);
            }
        }
        return String.copyValueOf(chars);
    }
    /**
     * format String to date
     * @param pattern
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDate(String pattern, String dateStr) throws ParseException {
        if (pattern == null)
            pattern = DATE_TIME_DEFAULT;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }
    /**
     * Get format date string
     * @param pattern
     * @param date
     * @return
     */
    public static String getDateStr(String pattern, Date date){
        if (pattern == null)
            pattern = DATE_TIME_DEFAULT;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
}
