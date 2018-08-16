package com.time.article.dao.handler;

import com.time.article.core.dao.handler.BaseEnumTypeHandler;
import com.time.article.core.enums.base.BaseEnum;
import com.time.article.dao.enums.business.log.LogEnum;
import com.time.article.dao.enums.business.permission.ResourceEnum;
import org.apache.ibatis.type.MappedTypes;

/**
 * 扫描指定枚举类 用于写入mybatis
 * @author suiguozhen on 18/07/17
 */
@MappedTypes(value =
        {
                ResourceEnum.class,
                LogEnum.class

        })
public class EnumTypeHandler<E extends BaseEnum> extends BaseEnumTypeHandler<E> {
    public EnumTypeHandler(Class<E> type) {
        super(type);
    }
}
