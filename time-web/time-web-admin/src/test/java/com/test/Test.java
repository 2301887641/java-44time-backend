package com.test;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.BusinessCodeEnums;

/**
 * @author suiguozhen on 18/08/20
 */
public class Test {
    @org.junit.Test
    public  void main(){
        System.out.println(new BusinessException(BusinessCodeEnums.DAO_RECORD_MISSED));
    }
}
