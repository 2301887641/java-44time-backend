package com.time.article.security.core.enums;

import com.time.article.core.enums.base.BaseEnum;
import lombok.Getter;

/**
 *  @author suiguozhen on 18/09/21
 */
@Getter
public enum LoginTypeEnum implements BaseEnum {
    /**1)redirect重定向 2)json返回json*/
    REDIRECT(1,"redirect"),JSON(2,"json");

    private Integer ordinal;
    private String label;

    LoginTypeEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
