package com.time.article.core.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author suiguozhen on 18/06/29
 */
@Getter
@Setter
public class BaseDto<PK> implements Serializable {
    private PK id;
}
