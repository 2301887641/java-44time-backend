package com.time.article.security.core.enums;

import com.time.article.core.enums.base.BaseEnum;
import lombok.Getter;

/**
 * 返回异常信息
 * @author suiguozhen on 18/10/06
 */
@Getter
public enum MessageEnum implements BaseEnum {
    /**
     * 获取用户信息失败
     */
    USER_INFO_FAILURE(500,"获取用户信息失败");

    private Integer ordinal;
    private String label;

    MessageEnum(Integer ordinal,String label){
        this.ordinal=ordinal;
        this.label=label;
    }
}
