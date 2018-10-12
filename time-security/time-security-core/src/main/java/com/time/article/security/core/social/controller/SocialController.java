package com.time.article.security.core.social.controller;

import com.time.article.security.core.enums.MessageEnum;
import com.time.article.security.core.social.properties.SocialUserInfo;
import com.time.exception.core.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 社交相关的控制器
 * @author suiguozhen on 18/10/08
 */
@Controller
public class SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 获取qq或微信登陆时的信息
     * @param request
     * @return
     */
    @GetMapping("/social/user")
    @ResponseBody
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        Connection<?> session = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        if(Objects.isNull(session)){
            throw new BusinessException(MessageEnum.USER_INFO_FAILURE);
        }
        return new SocialUserInfo(
                session.getKey().getProviderId(),
                session.getKey().getProviderUserId(),
                session.getDisplayName(),
                session.getImageUrl()
                );
    }
}
