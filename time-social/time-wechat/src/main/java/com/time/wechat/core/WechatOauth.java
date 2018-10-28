package com.time.wechat.core;

import com.time.exception.core.BusinessException;
import com.time.social.common.bean.Token;
import com.time.social.common.bean.UserInfo;
import com.time.social.common.core.Oauth;
import com.time.social.common.enums.BusinessExceptionEnum;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * 微信登陆
 * @author suiguozhen on 18/10/28
 */
public class WechatOauth extends Oauth {

    /**
     * 构造函数 设置appId、secret、callbackUrl
     * @param appId
     * @param secret
     * @param callbackUrl
     */
    public WechatOauth(String appId, String secret, String callbackUrl) {
        WechatConfig.create(appId,secret,callbackUrl);
    }

    /**
     * 开放平台授权地址
     *
     * @return
     */
    @Override
    public String getAuthorizeURL(HttpServletRequest request) {

        return String.format(
                WechatConfig.wechatConfigProperties.getProperty("wechat_redirectUrl"),
                WechatConfig.wechatConfigProperties.getProperty("wechat_appId"),
                WechatConfig.wechatConfigProperties.getProperty("wechat_callbackUrl"),
                WechatConfig.wechatConfigProperties.getProperty("")
                );
    }

    /**
     * 根据request获取access token
     *
     * @param request
     * @return
     */
    @Override
    protected Token getAccessTokenByRequest(HttpServletRequest request) {
        return null;
    }

    /**
     * 获取openId 唯一确定用户 多应用除外
     *
     * @param request
     * @return
     */
    @Override
    public Token getOpenId(HttpServletRequest request) {
        return null;
    }

    /**
     * 获取unionid 多个应用可以唯一确定用户
     *
     * @param request
     * @return
     */
    @Override
    public Token getUnionId(HttpServletRequest request) {
        return null;
    }

    /**
     * 获取qq用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UserInfo getUserInfo(Token token) {
        return null;
    }

    /**
     * 私有内部静态类 禁止外部访问
     */
    private static class WechatConfig {
        //wechatConfigProperties属性
        private static Properties wechatConfigProperties = new Properties();

        /**
         * 加载properties配置文件 使用静态代码块
         * 获取当前线程的类加载器
         */
        static {
            try {
                wechatConfigProperties.load((Thread.currentThread().getContextClassLoader().getResourceAsStream("wechatconfig.properties")));
            } catch (IOException e) {
                throw new BusinessException(BusinessExceptionEnum.DEFAULT_EXCEPTION, "启动微信登陆失败");
            }
        }

        /**
         * 设置app_ID、secret、callback_URI
         *
         * @param appId
         * @param secret
         * @param callbackUrl
         */
        private static void create(String appId, String secret, String callbackUrl) {
            wechatConfigProperties.setProperty("wechat_appId", appId);
            wechatConfigProperties.setProperty("wechat_secret", secret);
            wechatConfigProperties.setProperty("wechat_callbackUrl", callbackUrl);
        }
    }
}
