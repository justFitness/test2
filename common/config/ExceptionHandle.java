package com.spsy.common.config;


import com.spsy.common.config.exception.SysException;
import com.spsy.common.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 拦截自定义异常
     *
     * @param sysException 自定义异常
     * @return
     */
    @ExceptionHandler(value = SysException.class)
    public ResponseVo handle(SysException sysException) {
        log.info("自定义异常信息 ==>> {}" , sysException.getMessage());
        return ResponseVo.error(sysException.getCode(), sysException.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseVo paramException(MethodArgumentNotValidException exception) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("------参数校验不通过------");
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        String defaultMessage = objectError.getDefaultMessage();
        ResponseVo resultBody = ResponseVo.error(402, defaultMessage);
        log.info("请求地址 --- {}",request.getRequestURL());
        log.info("参数不合法信息 --- {}",resultBody);
        return resultBody;
    }


}
