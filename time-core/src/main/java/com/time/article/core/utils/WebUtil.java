package com.time.article.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**
 * web相关工具
 *
 * @author suiguozhen on 18/03/30
 */
public class WebUtil {

    /**
     * 判断当前请求是否是ajax请求
     * @param request
     * @return
     */
    public static Boolean isAjaxRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("X-Requested-With"))
                .map(head -> Objects.equals("XMLHttpRequest", head)).orElse(false);
    }
}
