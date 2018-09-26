package com.time.article.security.core.captcha.validate;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.time.article.security.core.captcha.Captcha;
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
import java.util.Properties;

/**
 * 验证码控制器
 *
 * @author suiguozhen on 18/09/21
 */
@RestController
public class ValidateCaptchaController {
    /**
     * 验证码实例类
     */
    @Autowired
    private DefaultKaptcha captchaProducer;
    /**
     * 安全配置属性
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 验证码在session中存储的键名
     */
    public static final String CAPTCHA_KEY = "captcha";

    /**
     * session缓存
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 请求验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaConfig(ServletRequestUtils.getStringParameter(request, "width", securityProperties.getCode().getCaptcha().getWidth()), ServletRequestUtils.getStringParameter(request, "height", securityProperties.getCode().getCaptcha().getHeight()));
        Captcha captcha = buildCaptchaImage();
        sessionStrategy.setAttribute(new ServletWebRequest(request), CAPTCHA_KEY, captcha);
        ImageIO.write(captcha.getImage(), "jpeg", response.getOutputStream());
    }

    /**
     * 图片验证码配置
     * @param width
     * @param height
     */
    private void captchaConfig(String width,String height){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width",width);
        properties.setProperty("kaptcha.image.height",height);
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length",securityProperties.getCode().getCaptcha().getLength());
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        captchaProducer.setConfig(new Config(properties));
    }

    /**
     * 生成验证码图片
     *
     * @return
     */
    private Captcha buildCaptchaImage() {
        String createText = captchaProducer.createText();
        return new Captcha(captchaProducer.createImage(createText), createText, securityProperties.getCode().getCaptcha().getExpireIn());
    }
}