package com.time.article.core.exception.base;

/**
 * 运行时异常基类
 * @author suiguozhen on 18/07/27
 */
public abstract class LogicException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**有参构造*/
    public LogicException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "异常状态码："+code+"      异常信息："+msg;
    }
}
