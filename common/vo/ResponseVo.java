package com.spsy.common.vo;

import com.spsy.common.config.constant.ExceptionEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Deng.Xiao
 * @Date: 2019/3/6
 * @Description: 规定200-正常 201-出错
 */
@Data
public class ResponseVo implements Serializable {

    private Object data;

    private Integer code;

    private String message;


    public ResponseVo() {
    }

    /**
     * 错误信息
     *
     * @param code 状态码
     * @param message 状态信息
     * @return
     */
    public static ResponseVo error(Integer code, String message) {
        ResponseVo vo = new ResponseVo();
        vo.code = code;
        vo.message = message;
        return vo;
    }

    /**
     * 错误信息
     *
     * @param statusEnum 状态码和信息枚举
     * @return
     */
    public static ResponseVo error(ExceptionEnum statusEnum) {
        ResponseVo vo = new ResponseVo();
        vo.code = statusEnum.getCode();
        vo.message = statusEnum.getMessage();
        return vo;
    }

    /**
     * 返回正确数据和信息
     *
     * @param data 所返回数据 - TODO 非状态信息
     * @return
     */
    public static ResponseVo OK(Object data) {
        ResponseVo vo = new ResponseVo();
        vo.data = data;
        vo.code = 200;
        vo.message = "SUCCESS";
        return vo;
    }

    /**
     * 返回正确状态信息
     *
     * @param code 状态码
     * @param message 状态信息
     * @return
     */
    public static ResponseVo OK(Integer code, String message) {
        ResponseVo vo = new ResponseVo();
        vo.code = code;
        vo.message = message;
        return vo;
    }

    /**
     * 返回正确数据和状态信息
     *
     * @param data 所返回的数据
     * @param code 状态码
     * @param message 状态信息
     * @return
     */
    public static ResponseVo OK(Object data,Integer code, String message) {
        ResponseVo vo = new ResponseVo();
        vo.data = data;
        vo.code = code;
        vo.message = message;
        return vo;
    }
}
