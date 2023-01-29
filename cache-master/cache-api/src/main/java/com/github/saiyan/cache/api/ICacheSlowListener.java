package com.github.saiyan.cache.api;

/**
 * 慢日志操作接口
 */
public interface ICacheSlowListener {

    //监听
    void listen(final ICacheSlowListenerContext context);

    //慢日志的阈值
    long slowerThanMills();

}
