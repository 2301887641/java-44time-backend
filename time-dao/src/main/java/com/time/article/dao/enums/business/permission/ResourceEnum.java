package com.time.article.dao.enums.business.permission;

import com.time.exception.enums.BaseEnum;
import lombok.Getter;

/**
 * 资源类型枚举
 * @author sui
 */
@Getter
public enum  ResourceEnum implements BaseEnum {
    /**资源类型*/
    MODULE(1,"菜单"), ACTION(2,"按钮");

    private Integer ordinal;
    private String label;

    ResourceEnum(Integer ordinal, String label){
        this.ordinal=ordinal;
        this.label=label;
    }

    @Override
    public String toString() {
        return label;
    }
}
