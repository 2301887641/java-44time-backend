package com.time.article.security.core.defaults;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.time.article.security.core.code.captcha.pojo.Captcha;
import com.time.article.security.core.code.api.AbstractCodeGenerator;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Properties;

/**
 * 默认验证码生成器
 *
 * @author suiguozhen on 18/09/26
 */
public class DefaultCaptchaGenerator extends AbstractCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 验证码实例
     */
    private DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

    /**
     * 生成验证码图片
     * @param request
     * @return
     */
    @Override
    public Captcha buildCode(ServletWebRequest request) {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width",
                ServletRequestUtils.getStringParameter(request.getRequest(), "width", String.valueOf(securityProperties.getCode().getCaptcha().getWidth()))
                );
        properties.setProperty("kaptcha.image.height",
                ServletRequestUtils.getStringParameter(request.getRequest(), "height", String.valueOf(securityProperties.getCode().getCaptcha().getHeight()))
                );
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", String.valueOf(securityProperties.getCode().getCaptcha().getLength()));
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        defaultKaptcha.setConfig(new Config(properties));
        String createText = defaultKaptcha.createText();
        return new Captcha(defaultKaptcha.createImage(createText), createText, securityProperties.getCode().getCaptcha().getExpireIn());
    }
}