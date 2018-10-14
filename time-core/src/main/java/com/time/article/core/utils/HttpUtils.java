package com.time.article.core.utils;

import com.time.exception.core.BusinessException;
import com.time.exception.enums.BaseEnum;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * httpUrlConnection 工具类
 *
 * @author suiguozhen on 18/10/13
 */
public class HttpUtils {

    public static void get(String httpUrl){
        URL url;
        URLConnection urlConnection;
        // 创建远程url连接对象
        try{
            url = new URL(httpUrl);
            urlConnection = url.openConnection();

        }catch(Exception e){
//            throw new BusinessException(BaseEnum,);
        }

    }


}
