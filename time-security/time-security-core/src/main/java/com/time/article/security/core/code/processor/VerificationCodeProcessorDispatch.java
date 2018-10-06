package com.time.article.security.core.code.processor;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnum;
import com.time.article.security.core.code.api.VerificationCodeProcessor;
import com.time.article.security.core.enums.VerificationCodeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 *  验证码处理器的分发器
 * @author suiguozhen on 18/10/03
 */
@Component
public class VerificationCodeProcessorDispatch {

    @Autowired
    private Map<String, VerificationCodeProcessor> codeProcessors;

    /**
     * 根据参数查找验证码处理器
     * @param type
     * @return
     */
    public VerificationCodeProcessor findValidateCodeProcessor(String type){
        String processor= type.toLowerCase() + "Processor";
        VerificationCodeProcessor verificationCodeProcessor = codeProcessors.get(processor);
        if(Objects.isNull(verificationCodeProcessor)){
            throw new BusinessException(RestCodeEnum.DAO_RECORD_MISSED);
        }
        return verificationCodeProcessor;
    }

    /**
     * 重载方法
     * @param type
     * @return
     */
    public VerificationCodeProcessor findValidateCodeProcessor(VerificationCodeTypeEnum type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }
}