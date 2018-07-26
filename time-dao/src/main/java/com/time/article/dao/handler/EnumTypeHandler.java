package com.time.article.dao.handler;

import com.time.article.core.dao.handler.BaseEnumTypeHandler;
import com.time.article.core.enums.base.BaseEnum;
import com.time.article.dao.enums.business.permission.ResourceEnum;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author suiguozhen on 18/07/17
 */
@MappedTypes(value =
        {
                ResourceEnum.class
        })
public class EnumTypeHandler<E extends BaseEnum> extends BaseEnumTypeHandler<E> {
    public EnumTypeHandler(Class<E> type) {
        super(type);
    }
}
