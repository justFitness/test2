package com.spsy.common.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Deng.Xiao.
 * @Date: 2019/3/23 15:45
 * @Description:
 */
public class JSONUtil {

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param object
     * @return
     */
    public static String parseJSON(Object object) {

        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * 将 JSON 转换成对应的类型
     *
     * @param json
     * @param t  对应的想转换成的类
     * @param <T>
     * @return 对应class类型
     */
    public static <T> T parseJSON(String json, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(json,t);
    }

    /**
     * 将 JSON 串转换成list
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonLIST(String json, Class<T[]> clazz) {

        Gson gson = new Gson();
        T[] ts = gson.fromJson(json, clazz);
        return new ArrayList<>(Arrays.asList(ts));
    }
}
