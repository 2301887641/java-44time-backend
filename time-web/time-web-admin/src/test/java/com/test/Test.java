package com.test;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnums;

/**
 * @author suiguozhen on 18/08/20
 */
public class Test {
    @org.junit.Test
    public  void main(){
       throw new BusinessException(RestCodeEnums.DAO_RECORD_MISSED);
    }
}
