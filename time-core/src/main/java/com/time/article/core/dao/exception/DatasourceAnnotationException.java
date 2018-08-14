package com.time.article.core.dao.exception;

import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.exception.base.LogicException;

/**
 * 自定义数据源注解 异常 异常是给后台人员看的不是给前台用户看的
 *
 * @author suiguozhen on 18/08/12
 */
public class DatasourceAnnotationException extends LogicException {
    /**
     * 有参构造
     *
     * @param code
     * @param msg
     */
    public DatasourceAnnotationException(Integer code, String msg) {
        super(code, msg);
    }

    public DatasourceAnnotationException() {
        super(RestCodeEnums.AOP_DYNAMIC_EXCEPTION.getCode(), RestCodeEnums.AOP_DYNAMIC_EXCEPTION.getInfo());
    }

    public DatasourceAnnotationException(String msg) {
        super(RestCodeEnums.AOP_DYNAMIC_EXCEPTION.getCode(), msg);
    }
}
