package com.time.article.core.exception.handler;

import com.time.article.core.message.result.Result;
import com.time.article.core.utils.WebUtils;
import com.time.exception.core.BusinessException;
import com.time.exception.core.ConsoleLogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局统一异常处理 任何地方的异常都可以捕获到
 * 异常状态只在后台使用log记录，并不返给前台 前台只接受网络连接异常即可
 * 在这里我们还可以获取到spring容器
 *
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

    /**
     * 业务异常处理
     * 这里一定要加上@ResponseBody注解 不然和thymeleaf模板冲突
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessExceptionHandler(BusinessException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!WebUtils.isAjaxRequest(request)) {
            /**重定向到thymeleaf的错误页面error需要先放在目录下*/
            response.sendRedirect("/error");
        }
        /**ajax请求 返回500*/
        return Result.failed(exception.getCode(), exception.getMsg());
    }

    /**
     * 控制台日志输入 当前异常只在控制台打印
     * @param exception
     */
    @ExceptionHandler(ConsoleLogException.class)
    public void consoleLogExceptionHandler(ConsoleLogException exception){
        log.error(exception.getMsg(),exception.getCode());
    }

}