package com.github.saiyan.cache.api;

/**
 * 慢日志监听器上下文
 */
public interface ICacheSlowListenerContext {

    //方法名称
    String methodName();

    //参数信息
    Object[] params();

    //方法结果
    Object result();

    //开始时间
    long startTimeMills();

    //结束时间
    long endTimeMills();

    //消耗时间
    long costTimeMills();

}
