package com.time.utils.http;

import com.time.exception.core.BusinessException;
import com.time.utils.enums.BusinessEnum;

import java.net.URL;
import java.net.URLConnection;

/**
 * httpUrlConnection 工具类
 *
 * @author suiguozhen on 18/10/13
 */
public class HttpUrlConnectionUtils {

    public static void get(String httpUrl){
        URL url;
        URLConnection urlConnection;
        // 创建远程url连接对象
        try{
            url = new URL(httpUrl);
            urlConnection = url.openConnection();
        }catch(Exception e){
            throw new BusinessException(BusinessEnum.CONNECTION);
        }

    }


}
