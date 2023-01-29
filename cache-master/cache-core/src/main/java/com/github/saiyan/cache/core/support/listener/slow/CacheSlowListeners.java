package com.github.saiyan.cache.core.support.listener.slow;

import com.github.saiyan.cache.api.ICacheSlowListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 慢日志监听工具类
 */
public final class CacheSlowListeners {

    private CacheSlowListeners(){}

    /**
     * 无
     * @return 监听类列表
     */
    public static List<ICacheSlowListener> none() {
        return new ArrayList<>();
    }

    /**
     * 默认实现
     * @return 默认
     */
    public static ICacheSlowListener defaults() {
        return new CacheSlowListener();
    }

}
