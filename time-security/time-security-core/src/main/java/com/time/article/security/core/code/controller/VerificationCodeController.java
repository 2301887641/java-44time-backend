package com.time.article.security.core.code.controller;

import com.time.article.security.core.code.processor.VerificationCodeProcessorDispatch;
import com.time.article.security.core.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码控制器
 *
 * @author suiguozhen on 18/09/21
 */
@Controller
public class VerificationCodeController {

    @Autowired
    private VerificationCodeProcessorDispatch verificationCodeProcessorDispatch;

    /**
     * 图片验证码和短信验证码生成
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @RequestMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    @ResponseBody
    public void create(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        verificationCodeProcessorDispatch.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
    }
}