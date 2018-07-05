package com.time.article.core.service.api;

import com.time.article.core.service.dto.BaseDto;

/**
 * @author suiguozhen on 18/06/26
 */
public interface BaseService<PK,Dto extends BaseDto<PK>> {
    Dto add();
}
