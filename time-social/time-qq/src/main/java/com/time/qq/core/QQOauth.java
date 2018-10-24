package com.time.qq.core;

import com.time.exception.core.BusinessException;
import com.time.exception.core.ConsoleLogException;
import com.time.qq.bean.AccessToken;
import com.time.qq.enums.BusinessEnum;
import com.time.social.common.core.Oauth;
import com.time.utils.core.HttpUrlConnectionUtils;
import com.time.utils.core.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * qq pc登陆
 * @author suiguozhen on 18/10/18
 */
public class QQOauth extends Oauth {

    /**
     * 构造函数 设置appId、appKey、callbackUrl
     *
     * @param appId
     * @param appKey
     * @param callbackUrl
     */
    public QQOauth(String appId, String appKey, String callbackUrl) {
        QQConnectConfig.create(appId, appKey, callbackUrl);
    }

    /**
     * 开放平台授权地址
     *
     * @return
     */
    @Override
    public String getAuthorizeURL() {
        Properties qqConnectConfigProperties = QQConnectConfig.qqConnectConfigProperties;
        return "redirect:" + String.format(
                qqConnectConfigProperties.getProperty("qq_redirect_url"),
                qqConnectConfigProperties.getProperty("qq_app_id"),
                qqConnectConfigProperties.getProperty("qq_callback_url")
        );
    }

    /**
     * 根据request获取access token
     *
     * @param request
     * @return
     */
    @Override
    protected AccessToken getAccessTokenByRequest(HttpServletRequest request) {
        //获取请求信息
        String queryString = request.getQueryString();
        if (Objects.isNull(queryString)) {
            return new AccessToken();
        }
        String regex = "code=(\\w+)&state=(\\w+)";
        Matcher matcher = StringUtils.matcher(regex, queryString);
        //没有匹配到分组
        if (!matcher.find()) {
            return new AccessToken();
        }
        String accessTokenUrl = String.format(
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_accessTokenURL"),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_app_id"),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_app_key"),
                matcher.group(1),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_callback_url")
        );
        String result = HttpUrlConnectionUtils.get(accessTokenUrl);
        if (Objects.isNull(result)) {
            return new AccessToken();
        }
        return getForEntity(result);
    }

    /**
     * 获取openId 唯一确定用户 多应用除外
     *
     * @return
     */
    @Override
    public AccessToken getOpenId(HttpServletRequest request) {
        AccessToken accessTokenEntity = getAccessTokenByRequest(request);
        String accessToken = accessTokenEntity.getAccessToken();
        if(Objects.isNull(accessToken)){
            throw new ConsoleLogException(BusinessEnum.DEFAULT_EXCEPTION.getOrdinal(),"获取access token失败");
        }
        String openIdUrl=String.format(
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_getOpenIDURL"),
                accessToken
                );
        String result = HttpUrlConnectionUtils.get(openIdUrl);
        return null;
    }

    /**
     * 转换成实体
     * @param result
     * @return
     */
    private AccessToken getForEntity(String result){
        String regex = "access_token=(\\w+)&expires_in=(\\d+)&refresh_token=(\\w+)";
        Matcher matcher = StringUtils.matcher(regex, result);
        if(matcher.find()){
            return new AccessToken(matcher.group(1),matcher.group(2),matcher.group(3));
        }
        return new AccessToken();
    }

    /**
     * 私有内部静态类 禁止外部访问
     */
    private static class QQConnectConfig {
        //qqConnectionProperties属性
        private static Properties qqConnectConfigProperties = new Properties();

        /**
         * 加载properties配置文件 使用静态代码块
         * 获取当前线程的类加载器
         */
        static {
            try {
                qqConnectConfigProperties.load((Thread.currentThread().getContextClassLoader().getResource("qqconnectconfig.properties")).openStream());
            } catch (IOException e) {
                throw new BusinessException(BusinessEnum.IOEXCEPTION);
            }
        }

        /**
         * 设置app_ID、app_KEY、callback_URI
         *
         * @param appId
         * @param appKey
         * @param callbackUrl
         */
        private static void create(String appId, String appKey, String callbackUrl) {
            qqConnectConfigProperties.setProperty("qq_app_id", appId);
            qqConnectConfigProperties.setProperty("qq_app_key", appKey);
            qqConnectConfigProperties.setProperty("qq_callback_url", callbackUrl);
        }
    }
}
