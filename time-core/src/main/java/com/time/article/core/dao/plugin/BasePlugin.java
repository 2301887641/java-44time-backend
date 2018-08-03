package com.time.article.core.dao.plugin;

import com.time.article.core.dao.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 自动处理时间
 * @author suiguozhen on 18/08/03
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class BasePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // sql执行的类型增 删 改 查
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        switch (sqlCommandType) {
            case INSERT:
                insert(invocation.getArgs()[1]);
                break;
            case UPDATE:
                update(invocation.getArgs()[1]);
                break;
            default:
                break;
        }
        return invocation.proceed();
    }

    /**
     * 实现plugin方法时判断一下目标类型，是本插件要拦截的对象才执行Plugin.wrap方法，
     * 否者直接返回目标本省，这样可以减少目标被代理的次数
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 添加操作    必须map中必须传递实体才可以修改时间
     *
     * @param obj
     */
    private void insert(Object obj) {
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            entity.setCreateTime(LocalDateTime.now());
            entity.setUpdateTime(LocalDateTime.now());
        }
    }

    /**
     * 修改操作 必须map中必须传递实体才可以修改时间
     *
     * @param obj
     */
    private void update(Object obj) {
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            entity.setUpdateTime(LocalDateTime.now());
        }
    }
}
