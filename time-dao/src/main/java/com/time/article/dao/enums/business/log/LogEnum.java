package com.time.article.dao.enums.business.log;

import com.time.article.core.enums.base.BaseEnum;
import lombok.Getter;

/**
 * 操作日志
 * @author suiguozhen on 18/08/15
 */
@Getter
public enum LogEnum  implements BaseEnum {
    /**日志操作类型*/
    LOG_SELECT(1,"查询"),
    LOG_DELETE(2,"删除"),
    LOG_UPDATE(3,"修改");

    private Integer ordinal;
    private String label;

    LogEnum(Integer ordinal, String label){
        this.ordinal=ordinal;
        this.label=label;
    }

    @Override
    public String toString() {
        return label;
    }
}
