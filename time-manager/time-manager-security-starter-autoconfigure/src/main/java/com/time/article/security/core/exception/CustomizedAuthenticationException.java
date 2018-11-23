package com.time.article.security.core.exception;

import com.time.exception.enums.BaseEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义认证异常 用于springSecurity中使用
 *
 * @author suiguozhen on 18/11/16
 */
public class CustomizedAuthenticationException extends AuthenticationException {

    private Integer code;

    private String msg;

    public CustomizedAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomizedAuthenticationException(String msg) {
        super(msg);
    }

    public CustomizedAuthenticationException(BaseEnum baseEnum, String msg) {
        super(msg);
        this.code = baseEnum.getOrdinal();
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
