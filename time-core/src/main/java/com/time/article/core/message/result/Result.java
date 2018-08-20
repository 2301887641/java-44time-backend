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

    /**
     * 静态
     * @param retCode
     * @param retInfo
     * @param data
     * @param <W>
     * @return
     */
    public static <W> Result of(int retCode, String retInfo, W data) {
        return new Result<>(retCode, retInfo, data);
    }

    /**
     * 成功 传递数据
     * @param data
     * @param <W>
     * @return
     */
    public static<W> Result success(W data){
        return new Result<>(RestCodeEnums.SUCCESS.getCode(),RestCodeEnums.SUCCESS.getInfo(),data);
    }

    /**
     * 默认成功
     * @return
     */
    public static Result success(){
        return new Result(RestCodeEnums.SUCCESS.getCode(),RestCodeEnums.SUCCESS.getInfo());
    }

    /**
     * 失败 只传递错误信息
     * @param retInfo
     * @param <w>
     * @return
     */
    public static<w> Result failed(String retInfo){
        return new Result<>(RestCodeEnums.DEFAULT_EXCEPTION.getCode(),retInfo);
    }

    /**
     * 失败 可以传递完整的状态码和信息
     * @param code
     * @param retInfo
     * @param <W>
     * @return
     */
    public static<W> Result failed(int code,String retInfo){
        return new Result<>(code,retInfo);
    }

    /**
     * 失败 可以传递完的状态码和数整据
     * @param code
     * @param retInfo
     * @param data
     * @param <W>
     * @return
     */
    public static<W> Result failed(int code,String retInfo,W data){
        return new Result<>(code,retInfo,data);
    }

    /**
     * 私有构造
     */
    private Result() {
    }

    /**
     * 构造 不传数据
     * @param retCode
     * @param retInfo
     */
    private Result(int retCode, String retInfo) {
        this.retCode = retCode;
        this.retInfo = retInfo;
    }

    /**
     * 构造全参
     * @param retCode
     * @param retInfo
     * @param result
     */
    private Result(int retCode, String retInfo, T result) {
        this.retCode = retCode;
        this.retInfo = retInfo;
        this.data = result;
    }
}
