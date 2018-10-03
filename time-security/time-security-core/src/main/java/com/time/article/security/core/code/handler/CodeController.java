package com.time.article.security.core.code.handler;

import com.time.article.core.message.result.Result;
import com.time.article.security.core.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制器
 *
 * @author suiguozhen on 18/09/21
 */
@Controller
public class CodeController {

    @Autowired
    private CodeProcessorDispatch codeProcessorDispatch;

    /**
     * 图片验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
//    @GetMapping("/captcha")
//    public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**生成验证码*/
//        Captcha captcha = (Captcha) captchaGenerator.buildCode(new ServletWebRequest(request));
        /**放入session*/
//        sessionStrategy.setAttribute(new ServletWebRequest(request), Captcha.CAPTCHA_KEY, captcha);
//        ImageIO.write(captcha.getImage(), "jpeg", response.getOutputStream());
//    }

    /**
     * 短信验证码
     *
     * @param request
     * @param response
     */
//    @GetMapping("/sms")
//    public Result createSms(HttpServletRequest request, HttpServletResponse response) {
        /**生成短信码*/
//        Sms sms = smsGenerator.buildCode(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), Sms.SMS_KEY, sms);
//        String mobile = request.getParameter("mobile");
//        if (Objects.isNull(mobile)) {
//            return Result.failed("请输入手机号");
//        }
//        smsSender.send(mobile, sms.getCode());
//        return Result.success();
//    }

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void create(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        codeProcessorDispatch.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
    }
}