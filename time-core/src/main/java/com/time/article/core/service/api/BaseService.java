package com.time.article.core.service.api;

        import com.time.article.core.service.dto.BaseDto;

        import java.util.List;

/**
 * @author suiguozhen on 18/06/26
 */
public interface BaseService<PK,Dto extends BaseDto,CriteriaDto extends Dto> {
    /**
     * 添加操作
     * @param dto
     * @return
     */
    PK insert(Dto dto);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Dto getById(Integer id);

    /**
     * 列表查询
     * @param criteriaDto
     * @return
     */
    List<Dto> getList(CriteriaDto criteriaDto);

    /**
     * 修改
     *
     * @param dto
     */
    void update(Dto dto);

    /**
     * 删除操作
     * @param id
     * @return
     */
    PK delete(PK id);
}
