package com.time.article.core.message.result;

import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.message.constant.Constants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 通用rest结果返回
 * 使用菱形规避@SuppressWarnings("unchecked")
 * @author suiguozhen on 18/04/14
 */
@Getter
@Setter
public class Result<T> implements Serializable{
    private int retCode;
    private String retInfo;
    private T data;

    /**静态*/
    public static <W> Result of(int retCode, String retInfo, W data) {
        return new Result<>(retCode, retInfo, data);
    }

    /**成功*/
    public static<W> Result success(W data){
        return new Result<>(RestCodeEnums.SUCCESS.getCode(),RestCodeEnums.SUCCESS.getInfo(),data);
    }

    /**成功*/
    public static Result success(){
        return new Result(RestCodeEnums.SUCCESS.getCode(),RestCodeEnums.SUCCESS.getInfo());
    }

    /**失败 可以传递完整的状态码*/
    public static<W> Result failed(int code,String retInfo){
        return new Result<>(code,retInfo);
    }

    /**失败 可以传递完整的状态码*/
    public static<W> Result failed(int code,String retInfo,W data){
        return new Result<>(code,retInfo,data);
    }

    /**构造*/
    private Result() {
    }

    /*构造*/
    private Result(int retCode, String retInfo) {
        this.retCode = retCode;
        this.retInfo = retInfo;
    }

    /*构造*/
    private Result(int retCode, String retInfo, T result) {
        this.retCode = retCode;
        this.retInfo = retInfo;
        this.data = result;
    }
}
