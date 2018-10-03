package com.time.article.security.core.code.processor;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnum;
import com.time.article.security.core.code.sms.pojo.Sms;
import com.time.article.security.core.code.sms.sender.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Objects;

/**
 * 短信验证码处理器
 * @author suiguozhen on 18/09/30
 */
@Component("smsProcessor")
public class SmsProcessor extends AbstractCodeProcessor<Sms>{

    @Autowired
    private SmsSender smsSender;

    @Override
    public void send(ServletWebRequest request, Sms sms) {
        String mobile = request.getParameter("mobile");
        if (Objects.isNull(mobile)) {
            throw new BusinessException(RestCodeEnum.MOBILE_MISSING);
        }
        smsSender.send(mobile, sms.getCode());
    }
}
