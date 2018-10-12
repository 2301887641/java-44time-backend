package com.time.article.core.controller.converter;

import com.time.exception.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * 前端枚举转换
 * @author suiguozhen on 18/07/17
 */
public class FrontEnumConverter<T extends BaseEnum> implements Converter<String, T> {
    private Class<T> clazz;

    public FrontEnumConverter(Class<T> enumType) {
        clazz = enumType;
    }

    @Override
    public T convert(String source) {
        for(T enumType : clazz.getEnumConstants()){
            if(enumType.getOrdinal().equals(Integer.parseInt(source))){
                return enumType;
            }
        }
        return null;
    }
}
