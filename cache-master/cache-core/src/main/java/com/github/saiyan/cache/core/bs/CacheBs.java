package com.github.saiyan.cache.core.bs;

import com.github.houbb.cache.api.*;
import com.github.saiyan.cache.core.core.Cache;
import com.github.saiyan.cache.core.support.evict.CacheEvicts;
import com.github.saiyan.cache.core.support.listener.remove.CacheRemoveListeners;
import com.github.saiyan.cache.core.support.listener.slow.CacheSlowListeners;
import com.github.saiyan.cache.core.support.load.CacheLoads;
import com.github.saiyan.cache.core.support.persist.CachePersists;
import com.github.saiyan.cache.core.support.proxy.CacheProxy;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.saiyan.cache.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存引导类
 */
public final class CacheBs<K,V> {

    private CacheBs(){}

    //创建对象实例
    public static <K,V> CacheBs<K,V> newInstance() {
        return new CacheBs<>();
    }

    //map 实现
    private Map<K,V> map = new HashMap<>();

    //大小限制
    private int size = Integer.MAX_VALUE;

    //驱除策略
    private ICacheEvict<K,V> evict = CacheEvicts.fifo();


     //删除监听类
    private final List<ICacheRemoveListener<K,V>> removeListeners = CacheRemoveListeners.defaults();

    /**
     * 慢操作监听类
     */
    private final List<ICacheSlowListener> slowListeners = CacheSlowListeners.none();

    /**
     * 加载策略
     */
    private ICacheLoad<K,V> load = CacheLoads.none();

    /**
     * 持久化实现策略
     */
    private ICachePersist<K,V> persist = CachePersists.none();

    /**
     * map 实现
     */
    public CacheBs<K, V> map(Map<K, V> map) {
        ArgUtil.notNull(map, "map");

        this.map = map;
        return this;
    }

    /**
     * 设置 size 信息
     */
    public CacheBs<K, V> size(int size) {
        ArgUtil.notNegative(size, "size");

        this.size = size;
        return this;
    }

    /**
     * 设置驱除策略
     */
    public CacheBs<K, V> evict(ICacheEvict<K, V> evict) {
        ArgUtil.notNull(evict, "evict");

        this.evict = evict;
        return this;
    }

    /**
     * 设置加载
     */
    public CacheBs<K, V> load(ICacheLoad<K, V> load) {
        ArgUtil.notNull(load, "load");

        this.load = load;
        return this;
    }

    /**
     * 添加删除监听器
     */
    public CacheBs<K, V> addRemoveListener(ICacheRemoveListener<K,V> removeListener) {
        ArgUtil.notNull(removeListener, "removeListener");

        this.removeListeners.add(removeListener);
        return this;
    }

    /**
     * 添加慢日志监听器
     */
    public CacheBs<K, V> addSlowListener(ICacheSlowListener slowListener) {
        ArgUtil.notNull(slowListener, "slowListener");

        this.slowListeners.add(slowListener);
        return this;
    }

    /**
     * 设置持久化策略
     */
    public CacheBs<K, V> persist(ICachePersist<K, V> persist) {
        this.persist = persist;
        return this;
    }

    /**
     * 构建缓存信息
     */
    public ICache<K,V> build() {
        Cache<K,V> cache = new Cache<>();
        cache.map(map);//设置map
        cache.evict(evict);//默认fifo
        cache.sizeLimit(size);//设置大小限制

        cache.load(load);
        cache.persist(persist);

        cache.removeListeners(removeListeners);
        cache.slowListeners(slowListeners);

        // 初始化 ：设置过期机制啥的
        cache.init();
        return CacheProxy.getProxy(cache);
    }

}
