package com.time.article.core.message.constant;

/**
 * 定义公共常量
 *
 * @author suiguozhen on 18/07/05
 */
public interface Constants {
    /**
     * -----------------结果集返回--------------------
     * 信息提示相关
     * 200默认请求成功
     * 500默认请求失败
     */
    Integer RESULT_SUCCESS_CODE = 200;
    Integer RESULT_FAILED_CODE = 500;
    String RESULT_SUCCESS_INFO = "成功";

    /**
     * -----------------树形结构相关---------------------
     */
    Integer TREE_PARENT_ID = 0;

    /**
     * -----------------ajax请求标识---------------------
     */
    String AJAX_REQUEST_TAG = "XMLHttpRequest";
}
