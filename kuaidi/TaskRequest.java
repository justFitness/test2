package com.spsy.order.util.kuaidi;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: liYinHai
 * @Date: 2019/05/07 14:25
 * @Description: 快递订阅属性类
 */
@Data
public class TaskRequest implements Serializable {
    private static final long serialVersionUID = -5535678492457254953L;
    /**  **/
    private String company;

    /**  **/
    private String number;

    /**  **/
    private String from;

    /**  **/
    private String to;

    /**  **/
    private String key;

    private Map parameters;

}
