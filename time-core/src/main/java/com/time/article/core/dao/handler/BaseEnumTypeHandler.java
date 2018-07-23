package com.time.article.core.dao.handler;

import com.time.article.core.enums.base.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis枚举处理
 * @author suiguozhen on 18/07/17
 */
public class BaseEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {
    private Class<E> type;
    private E[] enums;

    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setInt(i, parameter.getOrdinal());
        } else {
            ps.setObject(i, parameter.getOrdinal(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object ordinal = rs.getObject(columnName);
        return ordinal == null ? null : getEnum(ordinal);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object ordinal = rs.getObject(columnIndex);
        return ordinal == null ? null : getEnum(ordinal);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object ordinal = cs.getObject(columnIndex);
        return ordinal == null ? null : getEnum(ordinal);
    }

    /**
     * 获取枚举类
     * @param ordinal
     * @return
     */
    private E getEnum(Object ordinal) {
        for (E enumType : enums) {
            if (enumType.getOrdinal().equals(ordinal)) {
                return enumType;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + ordinal + ",请核对" + type.getSimpleName());
    }
}
