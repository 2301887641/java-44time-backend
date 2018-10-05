package com.time.article.security.core.enums;

import com.time.article.security.core.constants.SecurityConstants;

/**
 * 验证码类型枚举
 * @author suiguozhen on 18/10/03
 */
public enum VerificationCodeTypeEnum {
    /**
     * 短信验证码
     */
    SMS{
        /**
         * 验证验证码时,http请求中默认的携带验证码信息的参数的名称
         *
         * @return
         */
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    CAPTCHA{
        /**
         * 验证验证码时,http请求中默认的携带验证码信息的参数的名称
         *
         * @return
         */
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_CAPTCHA;
        }
    };

    /**
     * 验证验证码时,http请求中默认的携带验证码信息的参数的名称
     * @return
     */
    public abstract String getParamNameOnValidate();
}
