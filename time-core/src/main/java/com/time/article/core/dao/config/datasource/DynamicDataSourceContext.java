package com.time.article.core.dao.config.datasource;

/**
 * datasource的上下文
 * @author suiguozhen on 18/08/10
 */
public class DynamicDataSourceContext {
    private static final ThreadLocal<String> CONTEXT=new ThreadLocal<>();

    /**
     *设置数据源类型
     * @param name
     */
    public static void setDataSourceName(String name){
        CONTEXT.set(name);
    }

    /**
     * 获取数据源类型
     * @return
     */
    public static String getDataSourceName(){
        return CONTEXT.get();
    }

    /**
     * 清空数据源类型
     */
    public static void clearDataSourceName(){
        CONTEXT.remove();
    }
}
