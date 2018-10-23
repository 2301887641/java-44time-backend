package com.time.qq.core;

import com.time.exception.core.BusinessException;
import com.time.qq.bean.AccessToken;
import com.time.qq.enums.BusinessEnum;
import com.time.social.common.core.Oauth;
import com.time.utils.core.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suiguozhen on 18/10/18
 */
public class QQOauth implements Oauth {

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
    public AccessToken getAccessTokenByRequest(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if(!Objects.isNull(queryString)){
            //        code=F8DBABD3CBD5F52A586288DE6C9FB24E&state=44time
            String regex="code=(\\w+)&state=(\\w+)";
            Matcher matcher =  StringUtils.matcher(regex,queryString);
        }


//        String.format(
//                QQConnectConfig.qqConnectConfigProperties
//        )
        //        HttpUrlConnectionUtils.get();
        return null;
    }


    /**
     * 私有内部静态类 禁止外部访问
     */
    private static class QQConnectConfig {
        /**
         * qqConnectionProperties属性
         */
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
