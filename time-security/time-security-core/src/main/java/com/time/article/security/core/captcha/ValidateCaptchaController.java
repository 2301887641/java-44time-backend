package com.time.article.security.core.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制器
 * @author suiguozhen on 18/09/21
 */
@RestController
public class ValidateCaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    /**验证码在session中存储的键名*/
    private static final String CAPTCHA_KEY = "captcha";

    /**session缓存*/
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @GetMapping("/captcha")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captcha captcha = buildCaptchaImage();
        sessionStrategy.setAttribute(new ServletWebRequest(request),CAPTCHA_KEY,captcha);
        ImageIO.write(captcha.getImage(), "jpeg", response.getOutputStream());
    }

    /**
     * 生成验证码图片
     * @return
     */
    private Captcha buildCaptchaImage() {
            String createText = captchaProducer.createText();
            return new Captcha(captchaProducer.createImage(createText),CAPTCHA_KEY,60);
    }
}
