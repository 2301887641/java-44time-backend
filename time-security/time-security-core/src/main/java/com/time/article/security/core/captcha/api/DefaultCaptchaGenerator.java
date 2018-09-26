package com.time.article.security.core.captcha.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.time.article.security.core.captcha.pojo.Captcha;
import lombok.Setter;

import java.util.Properties;

/**
 * 默认验证码生成器
 *
 * @author suiguozhen on 18/09/26
 */
@Setter
public class DefaultCaptchaGenerator extends AbstractCaptchaGenerator {

    /**验证码实例*/
    private DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

    /**
     * 生成验证码图片
     *
     * @param width
     * @param height
     */
    @Override
    public Captcha buildCaptchaImage(String width, String height,String length,long expireIn) {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", width);
        properties.setProperty("kaptcha.image.height", height);
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length",length);
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        defaultKaptcha.setConfig(new Config(properties));
        String createText = defaultKaptcha.createText();
        return new Captcha(defaultKaptcha.createImage(createText), createText,expireIn);
    }
}