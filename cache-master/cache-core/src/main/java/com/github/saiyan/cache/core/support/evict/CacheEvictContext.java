package com.github.saiyan.cache.core.support.evict;

import com.github.saiyan.cache.api.ICache;
import com.github.saiyan.cache.api.ICacheEvictContext;

/**
 * 驱除策略
 *
 * 1. 新加的 key
 * 2. map 实现
 * 3. 淘汰监听器
 */
public class CacheEvictContext<K,V> implements ICacheEvictContext<K,V> {

    //新加的 key
    private K key;

    //cache 实现
    private ICache<K,V> cache;

    //最大的大小
    private int size;

    //返回key
    @Override
    public K key() {
        return key;
    }
    //设置key
    public CacheEvictContext<K, V> key(K key) {
        this.key = key;
        return this;
    }

    //返回缓存
    @Override
    public ICache<K, V> cache() {
        return cache;
    }
    //设置缓存
    public CacheEvictContext<K, V> cache(ICache<K, V> cache) {
        this.cache = cache;
        return this;
    }

    //返回大小
    @Override
    public int size() {
        return size;
    }
    //设置大小
    public CacheEvictContext<K, V> size(int size) {
        this.size = size;
        return this;
    }
}
