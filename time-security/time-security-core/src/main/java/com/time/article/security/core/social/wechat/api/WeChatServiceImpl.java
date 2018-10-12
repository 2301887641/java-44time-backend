package com.time.article.security.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.security.core.enums.MessageEnum;
import com.time.article.security.core.social.wechat.pojo.WeChatUserInfo;
import com.time.exception.core.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 *
 * @author suiguozhen on 18/10/10
 */
public class WeChatServiceImpl extends AbstractOAuth2ApiBinding implements WeChatService{
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    private ObjectMapper objectMapper=new ObjectMapper();

    public WeChatServiceImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 获取用户信息
     * 这里微信需要根据参数获取,而qq不需要
     * 微信走完oauth流程后,除了拿到accessToken还会拿到一个openId
     *
     * @param openId
     * @return
     */
    @Override
    public WeChatUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if(StringUtils.contains(response, "errcode")) {
            throw new BusinessException(MessageEnum.USER_INFO_FAILURE);
        }
        WeChatUserInfo profile;
        try {
            profile = objectMapper.readValue(response, WeChatUserInfo.class);
        } catch (Exception e) {
            throw new BusinessException(MessageEnum.USER_INFO_FAILURE);
        }
        return profile;
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，
     * 而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
