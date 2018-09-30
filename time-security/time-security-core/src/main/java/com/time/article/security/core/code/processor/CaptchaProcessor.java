package com.time.article.security.core.code.processor;

import com.time.article.security.core.code.captcha.pojo.Captcha;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author suiguozhen on 18/09/30
 */
public class CaptchaProcessor extends AbstractCodeProcessor<Captcha>{
    @Override
    public void save(ServletWebRequest request, Captcha captcha) {

    }
}
