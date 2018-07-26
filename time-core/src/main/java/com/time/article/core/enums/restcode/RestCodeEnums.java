package com.time.article.core.enums.restcode;

import lombok.Getter;

/**
 * rest枚举返回
 * @author suiguozhen on 18/04/14
 */

@Getter
public enum RestCodeEnums {
    /*操作成功*/
    SUCCESS("0000","操作成功"),
    CAPTCHA_ERROR("1000","验证码错误"),
    ARG_ERROR("5000", "参数错误"),
    MAT_ERROR("6000", "匹配失败"),
    MAT_ERROR_NOT_FOUND("6001", "未匹配到记录"),
    MAT_ERROR_HAS_EXIST("6002", "记录已存在"),
    MAT_ERROR_SMSCODE("6003", "短信验证码错误"),
    STA_ERROR("7000", "状态错误"),
    STA_ERROR_SMSCODE_NOT_SEND("7001", "未发送短信验证码"),
    STA_ERROR_SMSCODE_HAS_EXPIRED("7002", "验证码已过期"),
    SEC_ERROR("8000", "认证或授权失败"),
    SEC_ERROR_SMS_SENDCOUNT_LIMIT("8001", "您今天的短信发送次数已达到上限，请于明天继续操作"),
    SYS_ERROR("9000", "网络繁忙，请稍后重试"),
    SYS_ERROR_SERVICE("9001", "服务层错误"),
    SYS_ERROR_DAO("9002", "数据层错误");

    private String code;
    private String info;

    private RestCodeEnums(String code,String info){
        this.code=code;
        this.info=info;
    }

    @Override
    public String toString() {
        return info;
    }
}
