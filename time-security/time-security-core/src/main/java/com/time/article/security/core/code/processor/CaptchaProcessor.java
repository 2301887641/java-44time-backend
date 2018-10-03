package com.time.article.security.core.code.processor;

import com.time.article.security.core.code.captcha.pojo.Captcha;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图形验证码处理器
 * @author suiguozhen on 18/09/30
 */
@Component("captchaProcessor")
public class CaptchaProcessor extends AbstractCodeProcessor<Captcha>{

    @Override
    public void send(ServletWebRequest request, Captcha captcha) throws IOException {
        ImageIO.write(captcha.getImage(), "jpeg", request.getResponse().getOutputStream());
    }
}
