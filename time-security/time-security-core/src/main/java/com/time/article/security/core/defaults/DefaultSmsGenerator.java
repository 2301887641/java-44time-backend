package com.time.article.security.core.defaults;

import com.time.article.security.core.code.api.AbstractCodeGenerator;
import com.time.article.security.core.code.sms.pojo.Sms;
import com.time.article.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信码
 * @author suiguozhen on 18/09/29
 */
//@Component("smsGenerator")
public class DefaultSmsGenerator extends AbstractCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 生成码
     *
     * @param request
     * @return
     */
    @Override
    public Sms buildCode(ServletWebRequest request) {
        /**
         * 生成指定长度的随机数
         */
        return new Sms(RandomStringUtils.random(securityProperties.getCode().getSms().getLength(),"123456789"),securityProperties.getCode().getSms().getExpireIn());
    }
}