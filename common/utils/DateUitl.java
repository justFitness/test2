package com.spsy.common.utils;

import com.spsy.common.config.constant.ExceptionEnum;
import com.spsy.common.config.exception.SysException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: shiyunkai
 * @Date: 2019/04/24 10:58
 * @Description: 日期转换工具
 */
@Slf4j
public class DateUitl {

    /**
     * 日期转换为字符串
     * @param pattern 转换为字符串的格式,如"yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String Date2String(String pattern, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     *  日期转换为Long
     * @param pattern
     * @param date
     * @return
     */
    public static Long Date2Long(String pattern, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return Long.parseLong(sdf.format(date));
    }

    /**
     *  字符串转换成日期
     * @param str
     * @return
     */
    public static Date String2Date(String pattern, String str){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            log.error("异常信息：[{}]",e.getMessage());
            throw new SysException(ExceptionEnum.SYSTEM_EXCEPTION);
        }
    }

    /**
     *  Long转换成日期
     * @param str
     * @return
     */
    public static Date Long2Date(String pattern, Long str){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str.toString());
        } catch (ParseException e) {
            log.error("异常信息：[{}]",e.getMessage());
            throw new SysException(ExceptionEnum.SYSTEM_EXCEPTION);
        }
    }

}
