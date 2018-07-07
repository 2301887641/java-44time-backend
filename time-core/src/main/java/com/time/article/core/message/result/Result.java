package com.time.article.core.message.result;

import com.time.article.core.message.constant.Constants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页结果返回
 * 使用菱形规避@SuppressWarnings("unchecked")
 * @author suiguozhen on 18/04/14
 */
@Getter
@Setter
public class Result<T> implements Serializable{
    private String retCode;
    private String retInfo;
    private T data;

    public static<W> Result success(W data){
        return new Result<>(Constants.RESULT_SUCCESS_CODE,Constants.RESULT_SUCCESS_INFO,data);
    }

    public static <W> Result of(String retCode, String retInfo, W data) {
        return new Result<>(retCode, retInfo, data);
    }

    public static Result success(){
        return new Result(Constants.RESULT_SUCCESS_CODE,Constants.RESULT_SUCCESS_INFO);
    }

    public static Result failed(String retInfo){
        return new Result<>(Constants.RESULT_FAILED_CODE,retInfo);
    }

    private Result() {
    }

    private Result(String retCode, String retInfo) {
        this.retCode = retCode;
        this.retInfo = retInfo;
    }

    private Result(String retCode, String retInfo, T result) {
        this.retCode = retCode;
        this.retInfo = retInfo;
        this.data = result;
    }
}
