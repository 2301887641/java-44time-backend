package com.time.qq.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.exception.core.BusinessException;
import com.time.exception.core.ConsoleLogException;
import com.time.qq.bean.AccessToken;
import com.time.qq.bean.QQInfo;
import com.time.qq.enums.BusinessExceptionEnum;
import com.time.qq.enums.BusinessTypeEnum;
import com.time.social.common.bean.Token;
import com.time.social.common.bean.UserInfo;
import com.time.social.common.core.Oauth;
import com.time.utils.core.HttpUrlConnectionUtils;
import com.time.utils.core.StringUtils;
import com.time.utils.enums.BusinessEnum;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * qq登陆
 *
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
                qqConnectConfigProperties.getProperty("qq_redirectUrl"),
                qqConnectConfigProperties.getProperty("qq_appId"),
                qqConnectConfigProperties.getProperty("qq_callbackUrl")
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
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_accessTokenUrl"),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_appId"),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_appKey"),
                matcher.group(1),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_callbackUrl")
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
        String regex = "\"openid\":\"(\\w+)\"";
        return validateAccessToken(
                request,
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_getOpenIDUrl"),
                regex,
                BusinessTypeEnum.OPENID
        );
    }

    /**
     * 获取unionid 多个应用可以唯一确定用户
     *
     * @param request
     * @return
     */
    @Override
    public AccessToken getUnionId(HttpServletRequest request) {
        String regex = "\"unionid\":\"(\\w+)\"";
        return validateAccessToken(
                request,
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_getUnionIdUrl"),
                regex,
                BusinessTypeEnum.UNIONID
        );
    }

    /**
     * 获取qq用户信息
     *
     * @param token
     * @return
     */
    @Override
    public QQInfo getUserInfo(Token token) {
        String openId = token.getOpenId();
        if (Objects.isNull(openId)) {
            openId = token.getUnionId();
        }
        String userInfoUrl = String.format(
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_getUserInfoUrl"),
                token.getAccessToken(),
                QQConnectConfig.qqConnectConfigProperties.getProperty("qq_appId"),
                openId
        );
        String result = HttpUrlConnectionUtils.get(userInfoUrl);
        if (Objects.isNull(result)) {
            throw new ConsoleLogException(BusinessExceptionEnum.DEFAULT_EXCEPTION.getOrdinal(), "获取用户信息失败");
        }
        String retRegex="\"ret\": (0)";
        Matcher matcher = StringUtils.matcher(retRegex, result);
        if(!matcher.find()){
            throw new ConsoleLogException(BusinessExceptionEnum.DEFAULT_EXCEPTION,"返回用户信息失败");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        QQInfo qqInfo=null;
        try {
             qqInfo = objectMapper.readValue(result, QQInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //{
        //    "ret": 0,
        //    "msg": "",
        //    "is_lost":0,
        //    "nickname": "断肠人",
        //    "gender": "男",
        //    "province": "山东",
        //    "city": "聊城",
        //    "year": "1905",
        //    "constellation": "",
        //    "figureurl": "http:\/\/qzapp.qlogo.cn\/qzapp\/101440236\/025F59427C58E5BC55B37609393BB193\/30",
        //    "figureurl_1": "http:\/\/qzapp.qlogo.cn\/qzapp\/101440236\/025F59427C58E5BC55B37609393BB193\/50",
        //    "figureurl_2": "http:\/\/qzapp.qlogo.cn\/qzapp\/101440236\/025F59427C58E5BC55B37609393BB193\/100",
        //    "figureurl_qq_1": "http:\/\/thirdqq.qlogo.cn\/qqapp\/101440236\/025F59427C58E5BC55B37609393BB193\/40",
        //    "figureurl_qq_2": "http:\/\/thirdqq.qlogo.cn\/qqapp\/101440236\/025F59427C58E5BC55B37609393BB193\/100",
        //    "is_yellow_vip": "0",
        //    "vip": "0",
        //    "yellow_vip_level": "0",
        //    "level": "0",
        //    "is_yellow_year_vip": "0"
        //}
        return null;
    }

    /**
     * 验证access token 并返回
     *
     * @param request
     * @param qqUrl
     * @param regex
     * @param businessTypeEnum
     * @return
     */
    private AccessToken validateAccessToken(HttpServletRequest request, String qqUrl, String regex, BusinessTypeEnum businessTypeEnum) {
        AccessToken accessTokenEntity = getAccessTokenByRequest(request);
        String accessToken = accessTokenEntity.getAccessToken();
        if (Objects.isNull(accessToken)) {
            throw new ConsoleLogException(BusinessExceptionEnum.DEFAULT_EXCEPTION, "获取access token失败");
        }
        String result = HttpUrlConnectionUtils.get(String.format(
                qqUrl,
                accessToken
        ));
        if (Objects.isNull(result)) {
            throw new ConsoleLogException(BusinessExceptionEnum.DEFAULT_EXCEPTION.getOrdinal(), "获取" + businessTypeEnum.getLabel() + "失败");
        }
        Matcher matcher = StringUtils.matcher(regex, result);
        if (!matcher.find()) {
            throw new ConsoleLogException(BusinessExceptionEnum.DEFAULT_EXCEPTION.getOrdinal(), "匹配" + businessTypeEnum.getLabel() + "失败");
        }
        if (BusinessTypeEnum.OPENID.equals(businessTypeEnum)) {
            accessTokenEntity.setOpenId(matcher.group(1));
        } else {
            accessTokenEntity.setUnionId(matcher.group(1));
        }
        return accessTokenEntity;
    }

    /**
     * 转换成实体
     *
     * @param result
     * @return
     */
    private AccessToken getForEntity(String result) {
        String regex = "access_token=(\\w+)&expires_in=(\\d+)&refresh_token=(\\w+)";
        Matcher matcher = StringUtils.matcher(regex, result);
        if (matcher.find()) {
            return new AccessToken(matcher.group(1), matcher.group(2), matcher.group(3));
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
                throw new BusinessException(BusinessExceptionEnum.IOEXCEPTION);
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
            qqConnectConfigProperties.setProperty("qq_appId", appId);
            qqConnectConfigProperties.setProperty("qq_appKey", appKey);
            qqConnectConfigProperties.setProperty("qq_callbackUrl", callbackUrl);
        }
    }
}
