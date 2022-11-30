package com.ypjiao.mettingfilm.backend_utils.exception;

import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共异常处理模块
 */
@Slf4j
@ControllerAdvice
@EnableWebMvc
public class BaseExceptionHandler {
    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponseVo serviceExceptionHandler(
            HttpServletRequest request,CommonServiceException e){
        log.error("CommonServiceException,code{},message",e.getError_code(),e.getError_message());
        return BaseResponseVo.serviceException(e);
    }
}
