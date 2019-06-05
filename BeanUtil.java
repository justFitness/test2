package com.spsy.common.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Deng.Xiao.
 * @Date: 2019/4/6 13:24
 * @Description:
 */
public class BeanUtil {

    /**
     *
     * @param source 参数
     * @param targetClass 目标类
     * @param <T>
     * @return 返回targetClass类型
     */
    public  static <T> T fromBean(Object source,Class<T> targetClass) {
        return fromBean(source,targetClass,null);
    }


    public  static <T> T fromBean(Object source, Class<T> targetClass, Converter converter) {
        BeanCopier copier = BeanCopier.create(source.getClass(), targetClass, converter != null);
        T target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception ignore) {}
        copier.copy(source,target,converter);
        return target;
    }

    /**
     * 将参数转化成map
     *
     * @param obj 参数实体
     * @return
     */
    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        BeanMap m = BeanMap.create(obj);
        map.putAll(m);
        return map;
    }

}
