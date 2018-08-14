package com.time.article.core.controller.aop;

import com.time.article.core.controller.annotation.Custom_FieldLog;
import com.time.article.core.controller.exception.AccessLogException;
import com.time.article.core.message.constant.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 访问日志Aop
 *
 * @author suiguozhen on 18/08/13
 */
@Aspect
@Component
public class AccessLogAop {
    @Pointcut("@annotation(com.time.article.core.controller.annotation.Custom_MethodLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new AccessLogException();
        }
        /**获取请求map*/
        Map<String, String[]> requestMap = getRequestMap();
        if(requestMap.isEmpty()){
            //Todo记录注解即可
        }

        /**先对方法进行执行*/
        Object object = point.proceed();
        Object[] args = point.getArgs();
        for(Object arg:args){
            getAllFields(arg.getClass(),requestMap);
        }
        return object;
    }

    private  void getAllFields(final Class<?> clazz,Map<String,String[]> requestMap) throws  IllegalAccessException, InstantiationException {
        final List<Field> list= new ArrayList<>();
        Class<?> currentClass=clazz;
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        while(currentClass!=null){
            Field[] declaredFields = currentClass.getDeclaredFields();
            for(Field declaredField:declaredFields){
                String[] requestValue = requestMap.get(declaredField.getName());
                if(Objects.isNull(requestValue)){
                    continue;
                }
                temp= Constants.CUSTOM_ANNOTATION_MISSING;
                Custom_FieldLog annotation = declaredField.getAnnotation(Custom_FieldLog.class);
                if(!Objects.isNull(annotation)){
                    temp=annotation.value();
                }
                stringBuilder.append(temp);
                stringBuilder.append(requestValue[0]);
                stringBuilder.append("\r\n");
            }
            if(list.size()>0){
                Collections.addAll(list,declaredFields);
            }
            currentClass=currentClass.getSuperclass();
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * 获取请求并封装成map
     * @return
     */
    private Map<String,String[]> getRequestMap(){
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) requestAttribute;
        HttpServletRequest request = servletRequestAttribute.getRequest();
        return request.getParameterMap();
    }
}
