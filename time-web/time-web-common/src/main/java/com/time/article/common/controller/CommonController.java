package com.time.article.common.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.time.article.common.constants.SecurityConstants;
import com.time.article.core.message.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

/**
 * 通用controller
 * @author suiguozhen on 18/11/14
 */
@Controller
public class CommonController {

    @GetMapping(SecurityConstants.DEFAULT_CAPTCHA_URL)
    public Result captcha(HttpServletResponse response) throws IOException {
        Properties properties = new Properties();
        //验证码个数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,"4");
        //是否带有边框
        properties.setProperty(Constants.KAPTCHA_BORDER,"no");
        //字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,"33");
        //字体间隙
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE,"7");


        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image,"jpg", response.getOutputStream());
        return Result.success();
    }
}