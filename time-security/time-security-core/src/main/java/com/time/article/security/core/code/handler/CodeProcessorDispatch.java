package com.time.article.security.core.code.handler;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnum;
import com.time.article.security.core.code.processor.CodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 *  验证码处理器的分发器
 * @author suiguozhen on 18/10/03
 */
@Component
public class CodeProcessorDispatch {
    @Autowired
    private Map<String, CodeProcessor> codeProcessors;

    /**
     * 根据参数查找验证码处理器
     * @param type
     * @return
     */
    public CodeProcessor findValidateCodeProcessor(String type){
        String processor= type.toLowerCase() + "Processor";
        CodeProcessor codeProcessor = codeProcessors.get(processor);
        if(Objects.isNull(codeProcessor)){
            throw new BusinessException(RestCodeEnum.DAO_RECORD_MISSED);
        }
        return codeProcessor;
    }

}
