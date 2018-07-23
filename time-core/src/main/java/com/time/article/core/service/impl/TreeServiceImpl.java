package com.time.article.core.service.impl;

import com.time.article.core.dao.entity.TreeEntity;
import com.time.article.core.dao.mapper.TreeMapper;
import com.time.article.core.service.api.TreeService;
import com.time.article.core.service.converter.TreeConverter;
import com.time.article.core.service.dto.TreeDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suiguozhen on 18/04/23
 */
public class TreeServiceImpl<
        PK extends Serializable,
        DTO extends TreeDto<DTO, PK>,
        CriteriaDTO extends DTO,
        ENTITY extends TreeEntity<ENTITY, PK>,
        CriteriaENTITY extends ENTITY,
        CONVERTER extends TreeConverter<DTO,CriteriaDTO,ENTITY,CriteriaENTITY>,
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
        implements TreeService<CriteriaDTO,DTO, PK> {
    /**parent_id*/
    private static final Integer PARENT_ID = 0;

    @Override
    public List<DTO> converterToTree(List<DTO> treeList) {
        List<DTO> root = new ArrayList<>();
        Map<PK, DTO> map = new HashMap<>(16);
        treeList.forEach(tree -> {
            /**将每一个先放入到map集合中*/
            map.put(tree.getId(), tree);
            /**如果map集合中已经包含当前的父类id 放入到children中*/
            if (map.containsKey(tree.getParentId())) {
                map.get(tree.getParentId()).addChildren(tree);
                return;
            }
            /**如果当前项的parentId是0的话,放入root中*/
            if (PARENT_ID.equals(tree.getParentId())) {
                root.add(tree);
            }
        });
        return root;
    }
}
