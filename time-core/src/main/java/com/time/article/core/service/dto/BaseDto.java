package com.time.article.core.service.dto;

import com.time.article.core.controller.annotation.FieldLog;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author suiguozhen on 18/06/29
 */
@Getter
@Setter
public class BaseDto<PK> implements Serializable {
    @FieldLog("主键:设置为")
    private PK id;
}
