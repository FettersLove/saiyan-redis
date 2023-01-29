package com.github.saiyan.cache.core.support.evict;

import com.github.saiyan.cache.api.ICacheEvict;

/**
 * 丢弃策略
 *
 * @author saiyan
 * @since 0.0.2
 */
public final class CacheEvicts {

    private CacheEvicts(){}

    //无策略
    public static <K, V> ICacheEvict<K, V> none() {
        return new CacheEvictNone<>();
    }

    //先进先出
    public static <K, V> ICacheEvict<K, V> fifo() {
        return new CacheEvictFifo<>();
    }

    //LRU 驱除策略
    public static <K, V> ICacheEvict<K, V> lru() {
        return new CacheEvictLru<>();
    }

    /**
     * LRU 驱除策略
     * 基于双向链表 + map 实现
     */
    public static <K, V> ICacheEvict<K, V> lruDoubleListMap() {
        return new CacheEvictLruDoubleListMap<>();
    }


    /**
     * LRU 驱除策略
     *
     * 基于LinkedHashMap
     */
    public static <K, V> ICacheEvict<K, V> lruLinkedHashMap() {
        return new CacheEvictLruLinkedHashMap<>();
    }

    /**
     * LRU 驱除策略
     *
     * 基于 2Q 实现
     */
    public static <K, V> ICacheEvict<K, V> lru2Q() {
        return new CacheEvictLru2Q<>();
    }

    /**
     * LRU 驱除策略
     *
     * 基于 LRU-2 实现
     */
    public static <K, V> ICacheEvict<K, V> lru2() {
        return new CacheEvictLru2<>();
    }

    /**
     * LFU 驱除策略
     *
     * 基于 LFU 实现
     */
    public static <K, V> ICacheEvict<K, V> lfu() {
        return new CacheEvictLfu<>();
    }

    /**
     * 时钟算法
     */
    public static <K, V> ICacheEvict<K, V> clock() {
        return new CacheEvictClock<>();
    }

}
