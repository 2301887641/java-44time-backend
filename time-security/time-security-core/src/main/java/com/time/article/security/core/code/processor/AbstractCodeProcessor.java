package com.time.article.security.core.code.processor;

import com.time.article.security.core.code.sms.pojo.Sms;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author suiguozhen on 18/09/30
 */
public abstract class AbstractCodeProcessor<CODE extends Sms> implements CodeProcessor {

    @Override
    public void create(ServletWebRequest request) {
        CODE code = generate(request);
        save(request, code);
        send(request, code);
    }

    private CODE generate(ServletWebRequest request) {
        return null;
    }

    public abstract void save(ServletWebRequest request, CODE code);

    private void send(ServletWebRequest request, CODE code) {

    }

}
