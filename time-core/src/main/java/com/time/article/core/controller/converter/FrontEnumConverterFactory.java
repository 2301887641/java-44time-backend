package com.time.article.core.controller.converter;

import com.time.article.core.enums.base.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * 前端枚举转换器工厂
 * @author suiguozhen on 18/07/17
 */
public class FrontEnumConverterFactory implements ConverterFactory<String,BaseEnum> {
    private static final Map<Class,Converter> CONVERTER_MAP = new WeakHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter converter = CONVERTER_MAP.get(targetType);
        if(Objects.isNull(converter)){
            converter=new FrontEnumConverter(targetType);
        }
        return converter;
    }
}
