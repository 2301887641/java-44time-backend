package com.time.utils.core;


import java.util.Objects;

/**
 * 分页工具类
 *
 * @author suiguozhen on 18/04/12
 */
public class PaginationUtils {
    /**
     * 页码
     */
    private static final ThreadLocal<Integer> PAGE_NO = new ThreadLocal<>();
    /**
     * 每页显示数量
     */
    private static final ThreadLocal<Integer> PAGE_SIZE = new ThreadLocal<>();
    /**
     * 排序
     */
    private static final ThreadLocal<String> ORDER_BY = new ThreadLocal<>();
    /**
     * 默认
     */
    private static final Integer NUMBER = 1;
    private static final Integer SIZE = 10;
    private static final String ORDERBY = "orderby";

    public static Integer getPageNo() {
        return Objects.isNull(PAGE_NO.get()) ? NUMBER : PAGE_NO.get();
    }

    public static void setPageNo(Integer pageInfo) {
        PAGE_NO.set(pageInfo);
    }

    public static Integer getPageSize() {
        return Objects.isNull(PAGE_SIZE.get()) ? SIZE : PAGE_SIZE.get();
    }

    public static void setPagesize(Integer pageSize) {
        PAGE_SIZE.set(pageSize);
    }

    public static String getOrderBy() {
        return Objects.isNull(ORDER_BY.get()) ? ORDERBY : ORDER_BY.get();
    }

    public static void setOrderBy(String orderBy) {
        ORDER_BY.set(orderBy);
    }

    public static void removePageNo() {
        PAGE_NO.remove();
    }

    public static void removePageSize() {
        PAGE_SIZE.remove();
    }

    public static void removeOrderBy() {
        ORDER_BY.remove();
    }

}
