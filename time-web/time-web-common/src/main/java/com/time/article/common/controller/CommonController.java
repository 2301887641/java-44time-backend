package com.time.article.common.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.time.article.common.constants.SecurityConstants;
import com.time.article.common.enums.CommonEnum;
import com.time.exception.core.ConsoleLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

/**
 * 通用controller
 *
 * @author suiguozhen on 18/11/14
 */
@Controller
public class CommonController {

    @Autowired
    private DefaultKaptcha defaultCaptcha;

    /**
     * 生成验证码
     *
     * @param response
     */
    @GetMapping(SecurityConstants.DEFAULT_CAPTCHA_URL)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        Properties properties = new Properties();
        //验证码个数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //是否带有边框
        properties.setProperty(Constants.KAPTCHA_BORDER, "no");
        //字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "45");
        //字体间隙
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "7");
        defaultCaptcha.setConfig(new Config(properties));
        String text = defaultCaptcha.createText();
        HttpSession session = request.getSession();
        try {
            ImageIO.write(defaultCaptcha.createImage(text), "jpg", response.getOutputStream());
        } catch (Exception e) {
            //控制台显示
            throw new ConsoleLogException(CommonEnum.CAPTCHA);
        }
    }
}