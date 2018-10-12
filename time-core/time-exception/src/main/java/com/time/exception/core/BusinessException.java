package com.time.exception.core;

import com.time.exception.enums.BaseEnum;

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

    /**
     * 自定义信息构造
     * @param enums
     * @param message
     */
    public BusinessException(BaseEnum enums, String message){
        super(enums.getOrdinal(),message);
    }
}