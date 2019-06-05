package com.spsy.common.config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: Deng.Xiao
 * @Date: 2019/3/6
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class LogConfig {

    @Pointcut("execution(public * com.spsy.*.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:{}" , request.getRequestURL().toString());
        log.info("请求方式:{}" , request.getMethod());
        log.info("请求类方法:{}" , joinPoint.getSignature());
        log.info("请求类方法参数:{}" , new Gson().toJson(joinPoint.getArgs()));
        log.info("===============请求内容===============");

    }

    @AfterReturning(returning = "o", pointcut = "webLog()")
    public void methodAfterReturning(Object o) {
        log.info("--------------返回内容----------------");
        log.info("响应内容:{}" , new Gson().toJson(o));
        log.info("--------------返回内容----------------");

    }
}
