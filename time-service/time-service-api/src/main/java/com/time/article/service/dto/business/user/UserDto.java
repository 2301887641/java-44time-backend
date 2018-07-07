package com.time.article.service.dto.business.user;

import com.time.article.core.service.dto.BaseDto;
import com.time.article.core.service.dto.IntegerPkDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author suiguozhen on 18/06/28
 */
@Getter
@Setter
public class UserDto extends IntegerPkDto {
    private String name;
}
