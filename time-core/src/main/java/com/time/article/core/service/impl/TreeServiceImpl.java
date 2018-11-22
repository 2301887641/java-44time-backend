package com.time.article.core.service.impl;

import com.time.article.core.dao.entity.TreeEntity;
import com.time.article.core.dao.mapper.TreeMapper;
import com.time.article.core.message.constant.Constants;
import com.time.article.core.service.api.TreeService;
import com.time.article.core.service.converter.TreeConverter;
import com.time.article.core.service.dto.TreeDto;
import com.time.exception.constant.ConstantPool;
import com.time.exception.core.BusinessException;
import com.time.exception.enums.RestCodeEnum;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 树形结构格式化
 *
 * @author suiguozhen on 18/04/23
 */
public class TreeServiceImpl<
        PK extends Serializable,
        DTO extends TreeDto<DTO, PK>,
        CriteriaDTO extends DTO,
        ENTITY extends TreeEntity<ENTITY, PK>,
        CriteriaENTITY extends ENTITY,
        CONVERTER extends TreeConverter<DTO, CriteriaDTO, ENTITY, CriteriaENTITY>,
        MAPPER extends TreeMapper<ENTITY, PK>
        >
        extends BaseServiceImpl<
        PK,
        DTO,
        CriteriaDTO,
        ENTITY,
        CriteriaENTITY,
        CONVERTER,
        MAPPER
        >
        implements TreeService<CriteriaDTO, DTO, PK> {

    /**
     * 列表转换成树
     *
     * @param treeList
     * @return
     */
    @Override
    public List<DTO> converterToTree(List<DTO> treeList) {
        LinkedList<DTO> rootList = new LinkedList<>();
        Map<String, DTO> root = new HashMap<String, DTO>(16);
        for (int i = 0; i < treeList.size(); i++) {
            DTO dto = treeList.get(i);
            //父类parentId是0的话
            if (Constants.TREE_PARENT_ID.equals(dto.getParentId())) {
                rootList.add(dto);
                root.put("{" + dto.getId() + "}", dto);
                continue;
            }
            //如果当前父类已经在map中的话 说明当前是二级
            if (root.containsKey("{"+dto.getParentId()+"}")) {
                root.get("{" + dto.getParentId() + "}").addChildren(dto);
                continue;
            }
            //当前是三级或四。。
            String[] split = dto.getPath().split(",");
            if (split.length > 0) {
                if (root.containsKey(split[0])) {
                    recursion(root.get(split[0]).getChildren(),dto);
                }
            }
        }
        return rootList;
    }

    /**
     * 递归查询二级的children是否有匹配的
     * @param data
     * @param dto
     */
    private void recursion(List<DTO> data,DTO dto) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(dto.getParentId())) {
                data.get(i).addChildren(dto);
                break;
            }
            if(!CollectionUtils.isEmpty(data.get(i).getChildren())){
                recursion(data.get(i).getChildren(),dto);
            }
        }
    }

    /**
     * 查询path根据id进行like查询
     * @param id
     * @return
     */
    @Override
    public List<DTO> selectPathByLike(PK id) {
        return converter.entityToDto(mapper.selectPathByLike(id));
    }

    /**
     * 树形删除
     * @param id
     * @return
     */
    @Override
    public PK treeDelete(PK id) {
        if (!CollectionUtils.isEmpty(selectPathByLike(id))) {
            throw new BusinessException(RestCodeEnum.EXCEPTION, ConstantPool.DAO_TREE_DELETE_CHILD_DISABLED);
        }
        this.delete(id);
        return id;
    }

    /**
     * 树形修改
     * @param dto
     * @return
     */
    @Override
    public PK treeUpdate(DTO dto) {
        if (!Constants.TREE_PARENT_ID.equals(dto.getParentId())) {
            /**如果当前的id等于父类的id 说明是自己设置自己*/
            if (dto.getId().equals(dto.getParentId())) {
                throw new BusinessException(RestCodeEnum.EXCEPTION,ConstantPool.DAO_TREE_SET_SELF_PARENT);
            }
            String pathId = "{" + dto.getId() + "}";
            /**判断查询记录是否存在*/
            DTO parentDto = Optional.ofNullable(this.getById(dto.getParentId())).orElseThrow(() -> new BusinessException(RestCodeEnum.EXCEPTION,ConstantPool.DAO_RECORD_MISSED));
            /**如果选择的父类的path里面已经包含了当前子类的id 说明是父类设置成子类了*/
            if (parentDto.getPath().contains(pathId)) {
                throw new BusinessException(RestCodeEnum.EXCEPTION,ConstantPool.DAO_TREE_SET_SELF_PARENT);
            }
        }
        this.update(dto);
        return dto.getId();
    }
}
