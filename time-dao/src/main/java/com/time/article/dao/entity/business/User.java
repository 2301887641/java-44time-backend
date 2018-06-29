package com.time.article.dao.entity.business;

import com.time.article.core.dao.entity.BaseEntity;
import com.time.article.core.service.dto.IntegerPkDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author suiguozhen on 18/06/29
 */
@Getter
@Setter
public class User extends IntegerPkDto {
    private String name;
}
