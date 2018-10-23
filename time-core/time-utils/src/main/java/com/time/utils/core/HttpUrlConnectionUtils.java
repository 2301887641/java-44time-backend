package com.time.utils.core;

import com.time.exception.core.BusinessException;
import com.time.utils.enums.BusinessEnum;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * httpUrlConnection 工具类
 *
 * @author suiguozhen on 18/10/13
 */
public class HttpUrlConnectionUtils {

    public static void get(String httpUrl) {
        URL url;
        HttpsURLConnection urlConnection;
        String content;
        // 创建远程url连接对象
        try {
            /**
             * 使用url资源定位符
             */
            url = new URL(httpUrl);
            /**
             * 得到HttpURLConnection对象
             */
            urlConnection = (HttpsURLConnection) url.openConnection();
            /**
             * 设置请求属性 连接超时，读取超时的毫秒数
             */
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(6 * 1000);
            urlConnection.setReadTimeout(6 * 1000);
            /**
             * 请求成功的话
             */
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                /**
                 * 得到响应流
                 */
                InputStream inputStream = urlConnection.getInputStream();
                try (
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                ) {
                    while (!Objects.isNull((content = bufferedReader.readLine()))) {
                        System.out.println(content);
                        System.out.println("\r\n");
                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.CONNECTION);
        }
    }


}
