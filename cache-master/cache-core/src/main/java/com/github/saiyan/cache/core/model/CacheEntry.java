package com.github.saiyan.cache.core.model;

import com.github.saiyan.cache.api.ICacheEntry;

/**
 * key value 的明细信息
 */
public class CacheEntry<K,V> implements ICacheEntry<K,V> {

    //key
    private final K key;

    //value
    private final V value;

    //新建元素
    public static <K,V> CacheEntry<K,V> of(final K key,
                                           final V value) {
        return new CacheEntry<>(key, value);
    }

    public CacheEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public String toString() {
        return "EvictEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

}
