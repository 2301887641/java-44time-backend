package com.time.article.core.controller.schedule;

import java.util.TimerTask;

/**
 * 计划任务 执行器
 * @author suiguozhen on 18/08/15
 */
public interface ScheduleManager {
    /**
     * 执行定时器任务
     * @param task
     */
    void execute(TimerTask task);
}
