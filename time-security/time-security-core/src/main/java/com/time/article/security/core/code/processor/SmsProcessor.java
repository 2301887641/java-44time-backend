package com.time.article.security.core.code.processor;

import com.time.article.security.core.code.generator.AbstractCodeGenerator;
import com.time.article.security.core.code.sms.pojo.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author suiguozhen on 18/09/30
 */
public class SmsProcessor extends AbstractCodeProcessor<Sms>{

    @Autowired
    private AbstractCodeGenerator smsGenerator;

    @Override
    public void save(ServletWebRequest request, Sms sms) {

    }
}
