package com.time.article.core.dao.aop;

import com.time.article.core.dao.annotation.Datasource;
import com.time.article.core.dao.config.datasource.DynamicDataSourceContext;
import com.time.article.core.message.constant.Constants;
import com.time.exception.core.BusinessException;
import com.time.exception.enums.RestCodeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源aop
 * @author suiguozhen on 18/08/12
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "profile",name = "dynamicDatasource",havingValue = "true")
public class DatasourceAop implements Ordered {
    @Pointcut("@annotation(com.time.article.core.dao.annotation.Datasource)")
    public void datasource(){}

    @Around("datasource()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        /**获取当前执行的service上的方法 当前注解只能用于方法上*/
        if(!(signature instanceof MethodSignature)){
            throw new BusinessException(RestCodeEnum.ANNOTATION_BE_USED_TO_FUNC);
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        /**注解上的方法执行前 获取方法上的注解 并设置数据源*/
        Datasource annotation = method.getAnnotation(Datasource.class);
        DynamicDataSourceContext.setDataSourceName(annotation.value());
        try {
             return point.proceed();
        } finally{
            /**注解上的方法执行后执行 清除数据源*/
            DynamicDataSourceContext.clearDataSourceName();
        }
    }

    @Override
    public int getOrder() {
        return Constants.AOP_DYNAMIC_DATASOURCE_ORDER;
    }
}
