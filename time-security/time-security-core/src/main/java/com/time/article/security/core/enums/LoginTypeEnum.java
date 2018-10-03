package com.time.article.security.core.enums;

import com.time.article.core.enums.base.BaseEnum;
import lombok.Getter;

/**
 *  登陆方式: form表单、rest接口
 *  @author suiguozhen on 18/09/21
 */
@Getter
public enum LoginTypeEnum implements BaseEnum {
    /**1)redirect重定向 2)json返回json*/
    FORM(1,"form"),REST(2,"rest");

    private Integer ordinal;
    private String label;

    LoginTypeEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
