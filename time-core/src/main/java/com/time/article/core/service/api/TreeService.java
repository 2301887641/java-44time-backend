package com.time.article.core.service.api;

import com.time.article.core.service.dto.BaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author suiguozhen on 18/04/23
 */
public interface TreeService<CriteriaDto extends DTO,DTO extends BaseDto,PK extends Serializable> extends BaseService<PK,DTO,CriteriaDto>{
    /**
     * 列表转换成树
     * @param list
     * @return
     */
    List<DTO> converterToTree(List<DTO> list);
}
