package com.time.article.core.exception.handler;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.message.result.Result;
import com.time.article.core.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局统一异常处理 任何地方的异常都可以捕获到
 * 异常状态只在后台使用log记录，并不返给前台 前台只接受网络连接异常即可
 * 在这里我们还可以获取到spring容器
 * @author suiguozhen on 18/07/31
 */
@ControllerAdvice
@Slf4j
public class UnificationExceptionHandler implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @ExceptionHandler
    public Object businessExceptionHandler(Exception exception, HttpServletRequest request) {
        /**dao层异常*/
        if(exception instanceof BusinessException){
            /**ajax请求 返回500*/
            if(WebUtils.isAjaxRequest(request)){

                return new ResponseEntity<>(Result.failed(RestCodeEnums.DEFAULT_EXCEPTION.getCode(),RestCodeEnums.DEFAULT_EXCEPTION.getInfo()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else if(exception instanceof RuntimeException){
            StringBuffer requestURL = request.getRequestURL();
            requestURL.append("\n请求方式："+request.getMethod()+"\n");
            requestURL.append(exception.getMessage());
            log.error("请求地址："+requestURL.toString());
            /**ajax请求 返回500*/
            if(WebUtils.isAjaxRequest(request)){
                return new ResponseEntity<>(Result.failed(RestCodeEnums.DEFAULT_EXCEPTION.getCode(),RestCodeEnums.DEFAULT_EXCEPTION.getInfo()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            /**页面请求*/
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            return modelAndView;
        }
        log.error("其他异常信息如下:"+exception.getMessage());
        return null;
    }
}
