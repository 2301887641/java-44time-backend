package com.time.article.security.core.code.api;

import com.time.article.core.enums.restcode.RestCodeEnum;
import com.time.article.security.core.exception.VerificationCodeException;
import com.time.article.security.core.code.sms.pojo.Sms;
import com.time.article.security.core.enums.VerificationCodeTypeEnum;
import com.time.exception.core.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;
import java.util.Objects;

/**
 * 抽象验证码处理器
 *
 * @author suiguozhen on 18/09/30
 */
public abstract class AbstractVerificationCodeProcessor<CODE extends Sms> implements VerificationCodeProcessor {

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
    private VerificationCodeTypeEnum getCodeType() {
        /**
         * 首先,根据当前类名进行截取,返回Processor之前的字符串
         * 然后,将该字符串转大写 再获取其枚举
         */
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "Processor");
        return VerificationCodeTypeEnum.valueOf(type.toUpperCase());
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
     *
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
        return SESSION_KEY_PREFIX + getCodeType().toString().toUpperCase();
    }

    /**
     * 校验验证码
     *
     * @param request
     * @throws Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public void validate(ServletWebRequest request) {
        /**从缓存里面读取*/
        String sessionKey = getSessionKey();
        CODE codeInSession = (CODE) sessionStrategy.getAttribute(request, sessionKey);
        if(Objects.isNull(codeInSession)){
            throw new VerificationCodeException("请先发送验证码");
        }
        String requestCode;
        try {
            requestCode = ServletRequestUtils.getStringParameter(request.getRequest(), getCodeType().getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new VerificationCodeException("获取验证码失败,请重新尝试");
        }
        if (StringUtils.isBlank(requestCode)) {
            throw new VerificationCodeException("请输入验证码");
        }
        if (codeInSession.isExpired()) {
            /**移除缓存*/
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new VerificationCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), requestCode)) {
            throw new VerificationCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, sessionKey);
    }
}