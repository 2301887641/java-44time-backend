package com.time.article.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.core.message.constant.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * web相关工具
 *
 * @author suiguozhen on 18/03/30
 */
public class WebUtils {
    private static final String X_REQUESTED_WITH="x-requested-with";

    /**
     * 判断当前请求是否是ajax请求
     * @param request
     * @return
     */
    public static Boolean isAjaxRequest(HttpServletRequest request) {
        if(Constants.AJAX_REQUEST_TAG.equals(request.getHeader(X_REQUESTED_WITH))){
            return true;
        }
        return false;
    }

    /**
     * 对象转json
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
