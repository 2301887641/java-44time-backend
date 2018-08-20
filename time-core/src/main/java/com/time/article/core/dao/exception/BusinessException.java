package com.time.article.core.dao.exception;

import com.time.article.core.enums.base.BaseEnum;
import com.time.article.core.enums.restcode.BusinessCodeEnums;
import com.time.article.core.exception.base.LogicException;

/**
 * 业务层异常
 *
 * @author suiguozhen on 18/07/27
 */
public class BusinessException extends LogicException {

    /**
     * 有参构造
     * @param enums
     */
    public BusinessException(BaseEnum enums){
        super(enums.getOrdinal(),enums.getLabel());
    }
}
