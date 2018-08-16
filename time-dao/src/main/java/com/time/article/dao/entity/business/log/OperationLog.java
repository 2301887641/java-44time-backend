package com.time.article.dao.entity.business.log;

import com.time.article.core.dao.entity.BaseEntity;
import com.time.article.dao.enums.business.log.LogEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志
 * @author suiguozhen on 18/08/15
 */
@Getter
@Setter
public class OperationLog extends BaseEntity<Integer> {
    private String title;
    private String content;
    private String ip;
    private String className;
    private String method;
    private String result;
    private Integer userId;
    private LogEnum logType;
}
