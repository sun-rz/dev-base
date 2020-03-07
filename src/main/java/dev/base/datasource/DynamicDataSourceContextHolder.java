package dev.base.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 当前线程数据源,负责管理数据源的环境变量
 */
public class DynamicDataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    /**
     * 设置数据源名
     */
    public static void setDB(String dbType){
        log.info("切换到{}数据源", dbType);
        CONTEXT_HOLDER.set(dbType);
    }
    /**
     * 获取数据源名
     */
    public static String getDB(){
        return CONTEXT_HOLDER.get();
    }
    /**
     * 清理数据源名
     */
    public static void clearDB(){
        CONTEXT_HOLDER.remove();
    }
}