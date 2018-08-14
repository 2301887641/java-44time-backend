package com.time.article.core.controller.aop;

import com.time.article.core.controller.annotation.Custom_FieldLog;
import com.time.article.core.controller.annotation.Custom_MethodLog;
import com.time.article.core.controller.exception.AccessLogException;
import com.time.article.core.message.constant.Constants;
import com.time.article.core.service.dto.BaseDto;
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
 * 仅支持form-data方式的前台数据请求 json格式暂时无法支持 因为获取json很麻烦
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
        MethodSignature methodSignature = (MethodSignature) signature;
        /**获取请求并封装成map*/
        Map<String, String[]> requestMap = getRequestMap();
        if(requestMap.isEmpty()){
            //TODO 记录注解即可
            methodSignature.getMethod().getAnnotation(Custom_MethodLog.class).value();
        }
        /**先对方法进行执行*/
        Object object = point.proceed();
        for(Object arg:point.getArgs()){
            /**只对BaseDao的子类进行记录*/
            if(arg instanceof BaseDto){
                getAllFields(arg.getClass(),requestMap);
            }
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
     * 仅支持form-data方式的前台数据请求 json格式暂时无法支持 因为获取json很麻烦
     * @return
     */
    private Map<String,String[]> getRequestMap(){
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) requestAttribute;
        HttpServletRequest request = servletRequestAttribute.getRequest();
        return request.getParameterMap();
    }
}
