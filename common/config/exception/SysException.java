package com.spsy.common.config.exception;

import com.spsy.common.config.constant.ExceptionEnum;
import lombok.Data;

@Data
public class SysException extends RuntimeException {

    private Integer code;

    private String message;

    public SysException(ExceptionEnum statusEnum){
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
    }

    public SysException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
