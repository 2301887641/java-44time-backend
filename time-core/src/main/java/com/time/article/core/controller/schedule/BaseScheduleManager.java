package com.time.article.core.controller.schedule;

import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 计划任务基类
 *
 * @author suiguozhen on 18/08/15
 */
public class BaseScheduleManager {
    /**
     * 延期时间
     */
    public int delay = 1;
    /**
     * 延期单位
     */
    public TimeUnit unit = TimeUnit.SECONDS;
    /**
     * 线程池大小
     */
    public static int corePoolSize = 10;

    /**
     * 计划任务线程池对象
     */
    private static volatile ScheduledThreadPoolExecutor scheduleExecutor;

    /**
     * 获取实例方法
     */
    public static void getExecutor() {
        if (Objects.isNull(scheduleExecutor)) {
            synchronized (BaseScheduleManager.class) {
                if (Objects.isNull(scheduleExecutor)) {
                    scheduleExecutor = new ScheduledThreadPoolExecutor(corePoolSize);
                }
            }
        }
    }

    /**
     * 获取实例
     * @return
     */
    public static BaseScheduleManager getInstance(){
        getExecutor();
        return new BaseScheduleManager();
    }

    /**
     * 执行计划任务
     * @param task
     */
    public void execute(TimerTask task) {
        scheduleExecutor.schedule(task, delay, unit);
    }
}
