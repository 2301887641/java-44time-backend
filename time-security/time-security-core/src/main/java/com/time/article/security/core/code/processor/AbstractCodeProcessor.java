package com.time.article.security.core.code.processor;

import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.enums.restcode.RestCodeEnum;
import com.time.article.security.core.code.generator.AbstractCodeGenerator;
import com.time.article.security.core.code.sms.pojo.Sms;
import com.time.article.security.core.enums.CodeTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;
import java.util.Objects;

/**
 * 抽象验证码处理器
 *
 * @author suiguozhen on 18/09/30
 */
public abstract class AbstractCodeProcessor<CODE extends Sms> implements CodeProcessor {

    @Autowired
    private Map<String, AbstractCodeGenerator> generators;

    /**
     * session缓存
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 创建验证码的整个过程
     * 生成、保存、发送
     *
     * @param request
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        CODE code = generate(request);
        save(request, code);
        send(request, code);
    }

    /**
     * 生成码 验证码或短信码
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private CODE generate(ServletWebRequest request) {
        //sms或captcha
        String type = getCodeType().toString().toLowerCase();
        String generatorName = type + "Generator";
        AbstractCodeGenerator codeGenerator = generators.get(generatorName);
        if (Objects.isNull(codeGenerator)) {
            throw new BusinessException(RestCodeEnum.DAO_RECORD_MISSED);
        }
        //最后构建验证码 转成父类
        return (CODE) codeGenerator.buildCode(request);
    }

    /**
     * 获取请求的验证码的类型
     *
     * @return
     */
    private CodeTypeEnum getCodeType() {
        /**
         * 首先,根据当前类名进行截取,返回Processor之前的字符串
         * 然后,将该字符串转大写 再获取其枚举
         */
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "Processor");
        return CodeTypeEnum.valueOf(type.toUpperCase());
    }

    /**
     * 保存到session
     *
     * @param request
     * @param code
     */
    private void save(ServletWebRequest request, CODE code) {
        sessionStrategy.setAttribute(request, getSessionKey(), code);
    }

    /**
     * 最终发送验证码
     * @param request
     * @param code
     * @throws Exception
     */
    public abstract void send(ServletWebRequest request, CODE code) throws Exception;

    /**
     * 获取验证码的session key
     *
     * @return
     */
    private String getSessionKey() {
        return SESSION_KEY_PREFIX + getCodeType().toString().toLowerCase();
    }
}