package com.time.qq.config;

import com.time.exception.core.BusinessException;
import com.time.qq.enums.BusinessEnum;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取qqConnectConfig.properties配置
 *
 * @author suiguozhen on 18/10/18
 */
public class QQConnectConfig {
    /**
     * qqConnectionProperties属性
     */
    private static Properties qqConnectConfigProperties = new Properties();

    /**
     * 加载properties配置文件 使用静态代码块
     */
    static {
        /**
         * 获取当前线程的类加载器
         */
        try {
            qqConnectConfigProperties.load((Thread.currentThread().getContextClassLoader().getResource("qqconnectconfig.properties")).openStream());
        } catch (IOException e) {
            throw new BusinessException(BusinessEnum.IOEXCEPTION);
        }
    }

    public static Properties getQqConnectConfigProperties() {
        return qqConnectConfigProperties;
    }

    public static void setQQProperties(String app_ID, String app_KEY, String callback_URI) {
        qqConnectConfigProperties.setProperty("app_ID", app_ID);
        qqConnectConfigProperties.setProperty("app_KEY", app_KEY);
        qqConnectConfigProperties.setProperty("callback_URI", callback_URI);
    }
}