package com.time.article.admin.aop;

import com.time.article.admin.annotation.Log;
import com.time.article.admin.constants.Constants;
import com.time.article.core.controller.annotation.FieldLog;
import com.time.article.core.controller.schedule.BaseScheduleManager;
import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.service.dto.BaseDto;
import com.time.article.core.utils.WebUtils;
import com.time.article.dao.enums.business.log.LogEnum;
import com.time.article.service.api.business.log.OperationLogService;
import com.time.article.service.dto.business.log.OperationLogDto;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;
import java.util.Objects;
import java.util.TimerTask;

/**
 * 访问日志Aop
 * 仅支持form-data方式的前台数据请求 json格式暂时无法支持 因为获取json很麻烦
 *
 * @author suiguozhen on 18/08/13
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "profile", name = "operationLog", havingValue = "true")
@Slf4j
public class AccessLogAop{
    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.time.article.admin.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new BusinessException(RestCodeEnums.ANNOTATION_BE_USED_TO_FUNC);
        }
        OperationLogDto operationLogDto = new OperationLogDto();
        MethodSignature methodSignature = (MethodSignature) signature;
        /**获取请求并封装成map*/
        HttpServletRequest request = getRequest();
        /**执行目标方法*/
        Object object = point.proceed();
        LogEnum logType = methodSignature.getMethod().getAnnotation(Log.class).type();
        operationLogDto.setTitle(methodSignature.getMethod().getAnnotation(Log.class).value());
        operationLogDto.setContent(operationLogDto.getTitle());
        operationLogDto.setClassName(methodSignature.toString());
        operationLogDto.setUserId(1);
        operationLogDto.setLogType(logType);
        operationLogDto.setIp(request.getRemoteAddr());
        /**如果 请求的函数函数为空||查询操作||查询参数为空 不会比对参数*/
        if (point.getArgs().length < 1 || LogEnum.LOG_SELECT.getOrdinal().equals(logType.getOrdinal())) {
            operationLogDto.setResult(Constants.AOP_LOG_DEFAULT_RESULT);
            BaseScheduleManager.getInstance().execute(operationLogTask(operationLogDto));
            return object;
        }
        operationLogDto.setResult(WebUtils.toJson(object));
        /**如果是请求删除函数的话 需要记录返回值id*/
        if(LogEnum.LOG_DELETE.getOrdinal().equals(logType.getOrdinal())){
            BaseScheduleManager.getInstance().execute(operationLogTask(operationLogDto));
            return object;
        }
        /**遍历函数的参数执行比对属性*/
        for (Object arg : point.getArgs()) {
            /**只对BaseDao的子类进行记录*/
            if (arg instanceof BaseDto) {
                operationLogDto.setContent(getContent(arg.getClass(), request.getParameterMap()));
                BaseScheduleManager.getInstance().execute(operationLogTask(operationLogDto));
                break;
            }
        }
        return object;
    }

    /**
     * 记录操作日志任务
     *
     * @param operationLogDto
     * @return
     */
    private TimerTask operationLogTask(OperationLogDto operationLogDto) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    operationLogService.insert(operationLogDto);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
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
                FieldLog annotation = declaredField.getAnnotation(FieldLog.class);
                if (!Objects.isNull(annotation)) {
                    temp = annotation.value();
                }
                stringBuilder.append(temp).append(requestValue[0]).append("\r\n");
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
