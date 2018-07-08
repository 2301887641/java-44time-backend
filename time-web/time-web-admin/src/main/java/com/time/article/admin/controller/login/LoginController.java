package com.time.article.admin.controller.login;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.time.article.core.message.result.Result;
import com.time.article.security.shiro.Principal;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author suiguozhen on 18/07/03
 */
@RestController
public class LoginController {
    @Autowired
    private CasRestFormClient casRestFormClient;

    @Autowired
    private JwtGenerator generator;

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Value("${shiro.serviceUrl}")
    private String serviceUrl;

    /**
     * 验证码常量
     */
    private static final String CAPTCHA="captcha";

    /**
     * 登录操作:   登录时只需要传递username和password
     * 登录后的操作需要like this: http://localhost:8081/user/1?client_name&jwt&token=eyJjdHkiOiJKV1QiLC
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid Principal principal,
            BindingResult result
            ){
        if(result.hasErrors()){
            ObjectError objectError = result.getAllErrors().get(0);
            String objectName = objectError.getObjectName();
            Object[] arguments = objectError.getArguments();
            System.out.println(arguments[0]);
            String defaultMessage = objectError.getDefaultMessage();
            System.out.println(defaultMessage);
        }
        String captcha = (String)request.getSession().getAttribute(CAPTCHA);
        System.out.println(captcha);

        Map<String, Object> map = new HashMap<>(16);
        J2EContext context = new J2EContext(request, response);
        final ProfileManager<CasRestProfile> manager = new ProfileManager(context);
        final Optional<CasRestProfile> profile = manager.get(true);
        //获取ticket
        TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceUrl, profile.get(), context);
        //根据ticket获取用户信息
        final CasProfile casProfile = casRestFormClient.validateServiceTicket(serviceUrl, tokenCredentials, context);
        //生成jwt token
        String token = generator.generate(casProfile);
        map.put("token", token);
        return Result.success(map);
    }

    @RequestMapping("/captcha")
    public void buildCaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute(CAPTCHA, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
