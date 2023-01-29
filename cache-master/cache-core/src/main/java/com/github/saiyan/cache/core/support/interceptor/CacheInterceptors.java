package com.github.saiyan.cache.core.support.interceptor;

import com.github.saiyan.cache.api.ICacheInterceptor;
import com.github.saiyan.cache.core.support.interceptor.aof.CacheInterceptorAof;
import com.github.saiyan.cache.core.support.interceptor.common.CacheInterceptorCost;
import com.github.saiyan.cache.core.support.interceptor.evict.CacheInterceptorEvict;
import com.github.saiyan.cache.core.support.interceptor.refresh.CacheInterceptorRefresh;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存拦截器工具类
 * @author saiyan
 * @since 0.0.5
 */
public final class CacheInterceptors {

    /**
     * 默认通用
     * @return 结果
     */
    @SuppressWarnings("all")
    public static List<ICacheInterceptor> defaultCommonList() {
        List<ICacheInterceptor> list = new ArrayList<>();
        list.add(new CacheInterceptorCost());
        return list;
    }

    /**
     * 默认刷新
     * @return 结果
     */
    @SuppressWarnings("all")
    public static List<ICacheInterceptor> defaultRefreshList() {
        List<ICacheInterceptor> list = new ArrayList<>();
        list.add(new CacheInterceptorRefresh());
        return list;
    }

    /**
     * AOF 模式
     * @return 结果
     */
    @SuppressWarnings("all")
    public static ICacheInterceptor aof() {
        return new CacheInterceptorAof();
    }

    /**
     * 驱除策略拦截器
     * @return 结果
     */
    @SuppressWarnings("all")
    public static ICacheInterceptor evict() {
        return new CacheInterceptorEvict();
    }

}
