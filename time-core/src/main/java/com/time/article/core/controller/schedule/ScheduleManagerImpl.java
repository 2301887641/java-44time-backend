package com.time.article.core.controller.schedule;

import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 计划任务实现基类
 *
 * @author suiguozhen on 18/08/15
 */
public class ScheduleManagerImpl implements ScheduleManager {
    /**
     * 延期时间
     */
    public int delay = 10;
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
    public static ScheduledThreadPoolExecutor getExecutor() {
        if (Objects.isNull(scheduleExecutor)) {
            synchronized (ScheduleManagerImpl.class) {
                if (Objects.isNull(scheduleExecutor)) {
                    scheduleExecutor = new ScheduledThreadPoolExecutor(corePoolSize);
                }
            }
        }
        return scheduleExecutor;
    }

    /**
     * 获取实例
     * @return
     */
    public static ScheduleManagerImpl getInstance(){
        return new ScheduleManagerImpl();
    }

    @Override
    public void execute(TimerTask task) {
        scheduleExecutor.schedule(task, delay, unit);
    }
}
