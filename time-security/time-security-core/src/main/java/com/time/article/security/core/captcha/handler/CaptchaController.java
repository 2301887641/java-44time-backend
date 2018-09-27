package com.time.article.security.core.captcha.handler;

import com.time.article.security.core.captcha.generator.AbstractCaptchaGenerator;
import com.time.article.security.core.captcha.pojo.Captcha;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制器
 *
 * @author suiguozhen on 18/09/21
 */
@RestController
public class CaptchaController {
    /**
     * 安全配置属性
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * session缓存
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private AbstractCaptchaGenerator captchaGenerator;

    /**
     * 请求验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**生成验证码*/
        Captcha captcha = captchaGenerator.buildCaptchaImage(
                ServletRequestUtils.getStringParameter(request, "width", securityProperties.getCode().getCaptcha().getWidth()),
                ServletRequestUtils.getStringParameter(request, "height", securityProperties.getCode().getCaptcha().getHeight()),
                securityProperties.getCode().getCaptcha().getLength(),
                securityProperties.getCode().getCaptcha().getExpireIn()
        );
        sessionStrategy.setAttribute(new ServletWebRequest(request), Captcha.CAPTCHA_KEY, captcha);
        ImageIO.write(captcha.getImage(), "jpeg", response.getOutputStream());
    }
}