package com.time.article.core.dao.exception;

import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.exception.base.LogicException;

/**
 * 数据层异常
 *
 * @author suiguozhen on 18/07/27
 */
public class DaoException extends LogicException {
    /**
     * 空参构造
     * 采用默认dao层信息
     */
    public DaoException() {
        super(RestCodeEnums.DEFAULT_EXCEPTION.getCode(), RestCodeEnums.DEFAULT_EXCEPTION.getInfo());
    }

    /**
     * 有参构造
     * 采用默认code，自己传递msg
     */
    public DaoException(String msg) {
        super(RestCodeEnums.DAO_EXCEPTION.getCode(), msg);
    }

    /**
     * 有参构造
     * 自己传递code和msg 常量枚举中选择即可
     * @param code
     * @param msg
     */
    public DaoException(Integer code,String msg){
        super(code,msg);
    }
}
