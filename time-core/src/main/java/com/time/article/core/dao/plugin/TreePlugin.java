package com.time.article.core.dao.plugin;

import com.time.article.core.dao.entity.TreeEntity;
import com.time.article.core.dao.exception.DaoException;
import com.time.article.core.dao.mapper.TreeMapper;
import com.time.article.core.message.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author suiguozhen on 18/07/20
 * @Intercepts 注解: 为当前插件指定要拦截哪个对象的哪个方法,以及方法中的参数
 */
@Slf4j
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class TreePlugin implements Interceptor {
    //查询最大右值
    private static final String SELECT_MAX_RGT_SQL = ".selectMaxRgt";
    //根据parentId查询
    private static final String SELECT_BY_PARENT_ID_SQL = ".selectById";

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        String namespace = StringUtils.substringBeforeLast(mappedStatement.getId(), ".");
        Class entityClass = Class.forName(namespace);
        //判断TreeMap是entityClass的父类 或 TreeMap和entityClass相同
        if (TreeMapper.class.isAssignableFrom(entityClass)) {
            Executor executor = (Executor) invocation.getTarget();
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            switch (sqlCommandType) {
                case INSERT:
                    insert(executor, namespace, mappedStatement.getConfiguration(), (TreeEntity) args[1]);
                    break;
                case UPDATE:
                    update(executor, namespace, mappedStatement.getConfiguration(), (TreeEntity) args[1]);
                    break;
                default:
                    break;
            }
        }
        return invocation.proceed();
    }

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
     * 树形结构插入操作
     *
     * @param executor
     * @param namespace
     * @param configuration
     * @param entity
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public void insert(Executor executor, String namespace, Configuration configuration, TreeEntity entity) throws DaoException, IllegalAccessException, InstantiationException, SQLException {
        //左值，级别，
        int lft = 1, level = 1;
        MappedStatement statement;
        //如果前端没有传递parent_id字段 实例化自身
        if (Objects.isNull(entity.getParent())) {
            entity.setParent(entity.getClass().newInstance());
        }
        //如果parent_id是0的话
        if (Objects.isNull(entity.getParent().getId())) {
            entity.getParent().setId(Constants.TREE_PARENT_ID);
        }
        //获取parentId
        Integer parentId = (Integer) entity.getParent().getId();
        if(parentId!=0){
            throw new DaoException("数据库记录数少于1条");
        }
        //如果parent_id不等于0
        if (!Constants.TREE_PARENT_ID.equals(parentId)) {
            statement = configuration.getMappedStatement(namespace + SELECT_BY_PARENT_ID_SQL);
            System.out.println(statement);
            List<TreeEntity> query = executor.query(statement, wrapCollection(parentId), RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
            if (query.size() < 1) {
               throw new DaoException("数据库记录数少于1条");
            }

        } else {
            //需要查询最大右值 调用具体mapper类中的selectMaxRgt方法
            statement = configuration.getMappedStatement(namespace + SELECT_MAX_RGT_SQL);
            List<Integer> maxRgtList = executor.query(statement, null, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
            int maxRgt = maxRgtList.get(0);
            //如果最大右值是0则返回1，否则加1并返回
            if (maxRgt > 0) {
                lft = maxRgt + 1;
            }
        }
/*             {
            statement = configuration.getMappedStatement(namespace + ".getById");
            List<TreeEntity> parent = executor.query(statement, wrapCollection(entity.getParent().getId()), RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
            lft = parent.get(0).getRgt();
            level = parent.get(0).getLevel();

            statement = configuration.getMappedStatement(namespace + ".increaseRgt");
            executor.query(statement, wrapCollection(parent.get(0)), RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        }*/
        entity.setLft(lft);
        entity.setRgt(lft + 1);
        entity.setLevel(level);
    }

    private void update(Executor executor, String namespace, Configuration configuration, TreeEntity entity) {
        System.out.println(executor);
    }

    private Object wrapCollection(final Object object) {
        if (object instanceof Collection) {
            StrictMap<Object> map = new StrictMap<>();
            map.put("collection", object);
            if (object instanceof List) {
                map.put("list", object);
            }
            return map;
        } else if (object != null && object.getClass().isArray()) {
            StrictMap<Object> map = new StrictMap<>();
            map.put("array", object);
            return map;
        }
        return object;
    }
}
