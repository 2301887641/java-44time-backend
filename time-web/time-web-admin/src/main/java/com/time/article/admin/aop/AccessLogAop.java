package com.time.article.admin.aop;

import com.time.article.core.controller.annotation.Custom_OperationFieldLog;
import com.time.article.core.controller.annotation.Custom_OperationMethodLog;
import com.time.article.core.controller.exception.AccessLogException;
import com.time.article.core.controller.schedule.ScheduleManagerImpl;
import com.time.article.core.service.dto.BaseDto;
import com.time.article.core.utils.WebUtils;
import com.time.article.service.api.business.log.OperationLogService;
import com.time.article.service.dto.business.log.OperationLogDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
 *
 * @author suiguozhen on 18/08/13
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "profile", name = "operationLog", havingValue = "true")
public class AccessLogAop {
    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.time.article.core.controller.annotation.Custom_OperationMethodLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new AccessLogException();
        }
        OperationLogDto operationLogDto = new OperationLogDto();
        MethodSignature methodSignature = (MethodSignature) signature;
        /**获取请求并封装成map*/
        HttpServletRequest request = getRequest();
        /**执行目标方法*/
        Object object = point.proceed();
        operationLogDto.setTitle(methodSignature.getMethod().getAnnotation(Custom_OperationMethodLog.class).value());
        operationLogDto.setClassName(methodSignature.toString());
        operationLogDto.setUserId(1);
        operationLogDto.setIp(request.getRemoteAddr());
        operationLogDto.setResult(WebUtils.toJson(object));
        if (request.getParameterMap().isEmpty()) {
            ScheduleManagerImpl.getInstance().execute(operationLogTask(operationLogDto));
            return object;
        }
        for (Object arg : point.getArgs()) {
            /**只对BaseDao的子类进行记录*/
            if (arg instanceof BaseDto) {
                operationLogDto.setContent(getContent(arg.getClass(), request.getParameterMap()));
                ScheduleManagerImpl.getInstance().execute(operationLogTask(operationLogDto));
                break;
            }
        }
        return object;
    }

    public TimerTask operationLogTask(OperationLogDto operationLogDto){
        return new TimerTask() {
            @Override
            public void run() {
                operationLogService.insert(operationLogDto);
            }
        };
    }

    /**
     * 获取content 遍历dto的所有父类获取所有字段
     *
     * @param clazz
     * @param requestMap
     * @return
     */
    private String getContent(final Class<?> clazz, Map<String, String[]> requestMap) {
        final List<Field> list = new ArrayList<>();
        Class<?> currentClass = clazz;
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        while (currentClass != null) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String[] requestValue = requestMap.get(declaredField.getName());
                if (Objects.isNull(requestValue)) {
                    continue;
                }
                temp = declaredField.getName() + ":";
                Custom_OperationFieldLog annotation = declaredField.getAnnotation(Custom_OperationFieldLog.class);
                if (!Objects.isNull(annotation)) {
                    temp = annotation.value();
                }
                stringBuilder.append(temp).append(requestValue[0]).append("\r\n");
            }
            if (list.size() > 0) {
                Collections.addAll(list, declaredFields);
            }
            currentClass = currentClass.getSuperclass();
        }
        return stringBuilder.toString();
    }

    /**
     * 获取请求并封装成map
     * 仅支持application/form-data方式的前台数据请求 json格式暂时无法支持 因为获取json很麻烦
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) requestAttribute;
        HttpServletRequest request = servletRequestAttribute.getRequest();
        return request;
    }
}
