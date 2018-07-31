package com.time.article.admin.controller.business;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.time.article.core.dao.exception.DaoException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author suiguozhen on 18/07/30
 */
@Slf4j
//@Component
//@Aspect
public class AopExceptionHandler {
    @Pointcut("execution(* com.time.article.core.dao.plugin.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
//        System.out.println("URL : " + request.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + request.getMethod());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        try {
//            System.out.println("sdfsd");
//        }catch(Throwable throwable){
//            System.out.println("12344");
//        }
//        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret);
//    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public void arround(ProceedingJoinPoint pjp) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("执行Controller开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
        } catch (Throwable throwable) {
            handlerException(pjp, throwable);
        }
    }

    private void handlerException(ProceedingJoinPoint pjp, Throwable e) {
        if(e.getClass().isAssignableFrom(DaoException.class) ){
            DaoException messageCenterException = (DaoException)e;
        } else if (e instanceof RuntimeException) {
            System.out.println(2222);
        } else {
            System.out.println(33333);
        }
    }

}