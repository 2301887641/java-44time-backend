package com.time.article.core.exception.enums;

import com.time.article.core.message.constant.Constants;
import lombok.Getter;

/**
 * 业务异常枚举类
 *
 * @author suiguozhen on 18/07/27
 */
@Getter
public enum BusinessExceptionEnum {
    /**
     * dao层错误
     */
    DAO_EXCEPTION(3302, "数据请求错误"),

    /**默认异常*/
    DEFAULT_EXCEPTION(Constants.RESULT_FAILED_CODE,"网络繁忙，请稍后重试");

    private Integer ordinal;
    private String label;

    BusinessExceptionEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
