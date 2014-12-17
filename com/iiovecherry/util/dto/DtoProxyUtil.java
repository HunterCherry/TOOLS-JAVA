package com.iiovecherry.util.dto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 
 * @author D.I.hunter
 * @ClassName: DtoProxy.java
 * @date 2014-12-12
 * @Description:The PROXY DTO to mark up field change record
 */
public class DtoProxyUtil<T extends ProxyDto> implements MethodInterceptor {
    private T srcDto;
    
    public DtoProxyUtil(T srcDto) {
        this.srcDto = srcDto;
        if(srcDto==null) throw new NullPointerException();
    }

    @SuppressWarnings("unchecked")
    public T getDtoProxy() throws IllegalArgumentException, IllegalAccessException {
        Class<?> dtoClass = srcDto.getClass();     
        Object proxy = Enhancer.create(dtoClass, this);    
        if (proxy.getClass().getSuperclass() != dtoClass) {
            throw new IllegalArgumentException(dtoClass + " is not the super class for " + proxy.getClass());
        }
        initField(dtoClass,proxy);
        return (T) proxy;
    }

    private void initField(Class<?> dtoClass,Object proxy) throws IllegalArgumentException, IllegalAccessException {
        if (dtoClass == null)
            return;
        Field[] fs = dtoClass.getDeclaredFields();
        for (Field item : fs) {
            item.setAccessible(true);
            item.set(proxy, item.get(srcDto));
        }
        initField(dtoClass.getSuperclass(),proxy);
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        String name = getFieldName(arg1.getName());
        Object obj = arg3.invokeSuper(arg0, arg2);
        if (name != null) {
           srcDto.markChange(name);
        }
        return obj;
    }
    /**
     * 
     * @param arg1
     *            get Field name with method (only get set* method)
     * @return
     */
    private String getFieldName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (name.startsWith("set") && name.length() > 3) {
            if (name.length() > 4) {
                return name.substring(3, 4).toLowerCase() + name.substring(4);
            }
            return name.substring(3, 4).toLowerCase();
        }
        return null;
    }
}
