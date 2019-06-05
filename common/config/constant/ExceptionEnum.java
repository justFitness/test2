package com.spsy.common.config.constant;

public enum ExceptionEnum {

    SYSTEM_EXCEPTION(500, "系统异常"),
    ACCOUNT_ERROR(100000, "账号密码错误"),
    TOKEN_TIMEOUT(2000, "token失效"),
    ACTIVE_FAILURE(100002,"操作失败"),
    CRON_ERROR(100005,"转换时间cron格式异常"),
    APP_PUSH_ERROR(100007,"app推送异常"),
    ENUM_ERROR(10008, "枚举类型异常"),
    PAY_FAILURE(10010, "支付失败"),


    ACCOUNT_EXIST(100012, "手机号码已被占用"),
    IDCARD_EXIST(100013, "身份证已注册"),
    EXIT_ERROR(100014, "用户退出异常"),
    CAPTCHA_ERROR(100015, "验证码错误"),
    PASSWORD_UPDATE_ERROR(100017, "短信修改密码异常"),
    BEAN_ERROR(100018, "入参异常"),
    ;


    private Integer code;

    private String message;


    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
