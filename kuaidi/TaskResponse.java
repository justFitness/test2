package com.spsy.order.util.kuaidi;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liYinHai
 * @Date: 2019/05/07 14:48
 * @Description: TODO
 */
@Data
public class TaskResponse implements Serializable {

    private static final long serialVersionUID = 4793457454249785034L;

    /**  **/
    private boolean result;

    /**  **/
    private String returnCode;

    /**  **/
    private String message;

    public boolean isResult() {
        return result;
    }
}
