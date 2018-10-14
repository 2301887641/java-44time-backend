package com.time.article.core.utils;

import com.time.article.core.message.constant.Constants;
import com.time.article.core.message.result.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * hibernate验证工具
 * @author suiguozhen on 18/07/09
 */
public class ValidatorUtils {
    /**
     * hibernate validator获取第一个错误信息并返回
     * @param result
     * @return
     */
    public static Result validateHasError(BindingResult result) {
        FieldError fieldError = result.getFieldError();
        return Result.failed(Constants.RESULT_FAILED_CODE,fieldError.getDefaultMessage(),fieldError.getField());
    }
}
