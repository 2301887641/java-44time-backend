package com.time.article.core.dao.plugin;

import com.time.article.core.dao.entity.TreeEntity;
import com.time.article.core.dao.exception.BusinessException;
import com.time.article.core.dao.mapper.TreeMapper;
import com.time.article.core.enums.restcode.BusinessCodeEnums;
import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.message.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.*;

/**
 * 树形拦截插件
 *
 * @author suiguozhen on 18/07/20
 * @Intercepts 注解: 为当前插件指定要拦截哪个对象的哪个方法,以及方法中的参数
 */
@Slf4j
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class TreePlugin implements Interceptor {
    /**
     * 根据parentId查询
     */
    private static final String SELECT_BY_ID_SQL = ".selectById";
    /**
     * 根据id进行like查询
     */
    private static final String SELECT_BY_PARENT_ID_SQL = ".selectByParentId";
    /**
     * 根据id修改path
     */
    private static final String UPDATE_PATH_BY_ID_SQL = ".updatePathById";

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
    public void insert(Executor executor, String namespace, Configuration configuration, TreeEntity entity) throws IllegalAccessException, InstantiationException, SQLException {
        /**如果前端没有传递parent_id字段 实例化自身*/
        if (Objects.isNull(entity.getParent())) {
            entity.setParent(entity.getClass().newInstance());
        }
        /**如果parent_id是0的话*/
        if (Objects.isNull(entity.getParent().getId())) {
            entity.getParent().setId(Constants.TREE_PARENT_ID);
        }
        /**获取parentId 默认level=1*/
        Integer parentId = (Integer) entity.getParent().getId(), level = 1;
        entity.setPath("");
        /**如果parent_id不等于0 查询当前的parent_id*/
        if (!Constants.TREE_PARENT_ID.equals(parentId)) {
            MappedStatement statement = configuration.getMappedStatement(namespace + SELECT_BY_ID_SQL);
            List<TreeEntity> parentEntityList = executor.query(statement, parentId, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
            if (CollectionUtils.isEmpty(parentEntityList)) {
                throw new BusinessException(BusinessCodeEnums.DAO_RECORD_MISSED);
            }
            TreeEntity treeEntity = parentEntityList.get(0);
            level = treeEntity.getLevel() + 1;
            entity.setPath(new StringBuilder(treeEntity.getPath()).append("{").append(parentId).append("}").append(",").toString());
        }
        entity.setLevel(level);
    }

    /**
     * 树形结构修改操作
     *
     * @param executor
     * @param namespace
     * @param configuration
     * @param entity
     */
    private void update(Executor executor, String namespace, Configuration configuration, TreeEntity entity) throws SQLException, IllegalAccessException, InstantiationException {
        MappedStatement statement;
        /**查询当前节点*/
        statement = configuration.getMappedStatement(namespace + SELECT_BY_ID_SQL);
        List<TreeEntity> PreviousEntityList = executor.query(statement,entity.getId(), RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        if (CollectionUtils.isEmpty(PreviousEntityList)) {
            throw new BusinessException(BusinessCodeEnums.DAO_RECORD_MISSED);
        }
        /**得到的是之前的pojo数据*/
        TreeEntity previousEntity = PreviousEntityList.get(0);
        /**判断当前记录如果没有parent的话*/
        if (Objects.isNull(previousEntity.getParent())) {
            previousEntity.setParent(previousEntity.getClass().newInstance());
        }
        /**判断如果parent对象中没有id的话*/
        if (Objects.isNull(previousEntity.getParent().getId())) {
            previousEntity.getParent().setId(Constants.TREE_PARENT_ID);
        }
        /**当前要修改的parentId 之前的parentId*/
        Integer currentParentId = (Integer) entity.getParent().getId(), previousParentId = (Integer) previousEntity.getParent().getId();
        /**
         * 这里就不对这两个id做null判断了
         * 如果两个parentId相同不需要做操作
         * */
        if (currentParentId.equals(previousParentId)) {
            return;
        }
        Integer level = 1;
        /**当前的parentId不为0的话 将当前parent记录的path取出*/
        if (!Constants.TREE_PARENT_ID.equals(currentParentId)) {
            statement = configuration.getMappedStatement(namespace + SELECT_BY_ID_SQL);
            List<TreeEntity> currentEntityList = executor.query(statement, currentParentId, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
            if (CollectionUtils.isEmpty(currentEntityList)) {
                throw new BusinessException(BusinessCodeEnums.DAO_RECORD_MISSED);
            }
            /**设置path*/
            TreeEntity currentTreeEntity = currentEntityList.get(0);
            level = currentTreeEntity.getLevel() + 1;
            entity.setLevel(level);
            entity.setPath(new StringBuilder(currentTreeEntity.getPath()).append("{").append(currentParentId).append("},").toString());
        } else {
            entity.setLevel(level);
            entity.setPath("");
        }
        /**开始递归查找entity.getId() 下级都需要设置*/
        Integer primaryId = (Integer) entity.getId();
        recursion(executor, namespace, configuration, primaryId, entity.getPath(), level);
    }

    /**
     * 递归需要转换为迭代
     * @param executor
     * @param namespace
     * @param configuration
     * @param parentId
     * @param parentPath
     * @param level
     * @throws SQLException
     */
    private void recursion(Executor executor, String namespace, Configuration configuration, Integer parentId, String parentPath, Integer level) throws SQLException {
        MappedStatement statement = configuration.getMappedStatement(namespace + SELECT_BY_PARENT_ID_SQL);
        List<TreeEntity> treeEntityList = executor.query(statement, parentId, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        if (CollectionUtils.isEmpty(treeEntityList)) {
            return;
        }
        Integer parentLevel = ++level;
        for (TreeEntity entity : treeEntityList) {
            statement = configuration.getMappedStatement(namespace + UPDATE_PATH_BY_ID_SQL);
            executor.update(statement, new Object[]{parentLevel, parentPath + "{" + parentId + "},", entity.getId()});
            recursion(executor, namespace, configuration, (Integer) entity.getId(), parentPath + "{" + parentId + "},", parentLevel);
        }
    }
}
